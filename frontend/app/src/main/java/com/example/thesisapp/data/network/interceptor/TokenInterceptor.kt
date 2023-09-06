package com.example.thesisapp.data.network.interceptor

import com.example.thesisapp.config.ConfigurationProvider
import com.example.thesisapp.data.network.ThesisApi
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.net.HttpURLConnection
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val thesisApi: ThesisApi,
    private val configurationProvider: ConfigurationProvider
): Interceptor {

    companion object {
        private const val TOKEN_HEADER = "Authorization"
    }

    private val isReloginInProgress: AtomicBoolean = AtomicBoolean(false)
    private val syncObject = Object()

    private val Response.isReloginNeeded: Boolean
        get() = code == HttpURLConnection.HTTP_UNAUTHORIZED

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val responseWithCurrentToken = proceedWithToken(chain, originalRequest)

        if (responseWithCurrentToken.isReloginNeeded.not()) {
            return responseWithCurrentToken
        }

        Timber.i("Relogin needed")

        return if (isReloginInProgress.compareAndSet(false, true)) {
            Timber.i("Going to request new token")
            runBlocking {
                reloginAndProceed(chain, responseWithCurrentToken, originalRequest)
            }
        } else {
            Timber.i("Relogin already in progress, waiting")
            waitForReloginAndProceed(chain, originalRequest, responseWithCurrentToken)
        }
    }

    private suspend fun reloginAndProceed(
        chain: Interceptor.Chain,
        responseWithCurrentToken: Response,
        originalRequest: Request
    ): Response {
        return if (relogin()) {
            isReloginInProgress.set(false)
            synchronized(syncObject) {
                Timber.i("Relogin successful, notifying waiting requests")
                syncObject.notifyAll()
            }
            proceedWithToken(chain, originalRequest, responseWithCurrentToken)
        } else {
            isReloginInProgress.set(false)
            synchronized(syncObject) {
                Timber.i("Relogin failed, notifying waiting requests")
                syncObject.notifyAll()
            }
            return responseWithCurrentToken
        }
    }

    private suspend fun relogin(): Boolean {
        //val email = configurationProvider.email

        /*if (email == null || sessionPassword == null) {
            Timber.w("Not all information is available for relogin")
            return false
        }*/

        //val loginResponse =


        return true/*if (loginResponse is NetworkResult) {
            selectConfiguration(selectedConfig)
        } else {
            Timber.w("Login call failed")
            false
        }*/
    }

    private fun waitForReloginAndProceed(
        chain: Interceptor.Chain,
        originalRequest: Request,
        responseWithCurrentToken: Response
    ): Response {
        synchronized(syncObject) {
            Timber.i("Waiting for new token")
            syncObject.wait()
            Timber.i("Notified, retrying request")
            return proceedWithToken(chain, originalRequest, responseWithCurrentToken)
        }
    }

    private fun proceedWithToken(
        chain: Interceptor.Chain,
        originalRequest: Request,
        originalResponse: Response? = null
    ): Response {
        val token = configurationProvider.token ?: ""

        val newRequest = originalRequest.newBuilder()
            .addHeader(TOKEN_HEADER, token)
            .build()

        originalResponse?.close()
        return chain.proceed(newRequest)
    }
}