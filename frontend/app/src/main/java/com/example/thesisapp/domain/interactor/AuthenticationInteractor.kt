package com.example.thesisapp.domain.interactor

import com.example.thesisapp.config.ConfigurationProvider
import com.example.thesisapp.data.network.source.AuthenticationNetworkDataSource
import com.example.thesisapp.domain.model.LoginCredentials
import com.example.thesisapp.domain.model.RegistrationCredentials
import com.example.thesisapp.util.NetworkNoResult
import com.example.thesisapp.util.NetworkResponse
import com.example.thesisapp.util.NetworkResult
import timber.log.Timber
import javax.inject.Inject

class AuthenticationInteractor @Inject constructor(
    private val authenticationNetworkDataSource: AuthenticationNetworkDataSource,
    private val configurationProvider: ConfigurationProvider
) {

    suspend fun loginWithCredentials(
        credentials: LoginCredentials,
        rememberMe: Boolean
    ): NetworkResponse<Boolean> {
        configurationProvider.username = credentials.username

        if (rememberMe) {
            configurationProvider.password = credentials.password
        }

        return login(credentials)
    }

    suspend fun loginUsingSavedCredentials(): NetworkResponse<Boolean> {
        val credentials = getSavedCredentials()

        return if (credentials != null) {
            login(credentials)
        } else {
            Timber.w("No saved credentials found")
            NetworkResult(false)
        }
    }

    private suspend fun login(credentials: LoginCredentials): NetworkResponse<Boolean> {
        return when (val loginResponse = authenticationNetworkDataSource.login(credentials)) {
            is NetworkResult -> {
                configurationProvider.accessToken = loginResponse.result.access_token
                configurationProvider.refreshToken = loginResponse.result.refresh_token

                NetworkResult(true)
            }
            is NetworkNoResult -> {
                configurationProvider.clearSession()
                loginResponse
            }
        }
    }

    suspend fun register(credentials: RegistrationCredentials): NetworkResponse<Boolean> {
        return when (
            val registrationResponse = authenticationNetworkDataSource.register(credentials)
        ) {
            is NetworkResult -> NetworkResult(true)
            is NetworkNoResult -> registrationResponse
        }
    }

    private fun getSavedCredentials(): LoginCredentials? {
        return configurationProvider.getRememberedCredentials()
    }
}