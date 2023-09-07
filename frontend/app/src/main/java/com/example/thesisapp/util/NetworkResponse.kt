package com.example.thesisapp.util

import com.example.thesisapp.data.network.model.ErrorResponseJsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import timber.log.Timber
import java.io.EOFException
import java.io.IOException
import java.net.UnknownHostException

/**
 *                         NetworkResult<T>
 *                       /
 *                     /
 *  NetworkResponse<T>                       NetworkThrottlingError
 *                     \                   /  NetworkIOError
 *                      \                /  /
 *                       NetworkNoResult -<
 *                                       \ \
 *                                        \ UnknownHostError
 *                                         NetworkHttpError
 */
sealed class NetworkResponse<out T : Any>

sealed class NetworkNoResult : NetworkResponse<Nothing>()

object NetworkIOError : NetworkNoResult()

object UnknownHostError : NetworkNoResult()

class NetworkHttpError(val errorMessage: String? = null, val code: Int? = null) : NetworkNoResult()

class NetworkResult<out T : Any>(val result: T) : NetworkResponse<T>()

/**
 * Executes the given network call and handles the exceptions
 * Wraps the results in a [NetworkResponse]
 */
@Suppress("BlockingMethodInNonBlockingContext")
suspend fun <T : Any> apiCall(block: suspend () -> T): NetworkResponse<T> {
    return try {
        val networkResult = block.invoke()
        NetworkResult(networkResult)
    } catch (unknownHost: UnknownHostException) {
        Timber.d(unknownHost)
        UnknownHostError
    } catch (e: IOException) {
        Timber.d(e)
        Timber.d(e.localizedMessage)
        NetworkIOError
    } catch (httpException: HttpException) {
        Timber.d(httpException)
        val errorMessage = getErrorMessage(httpException)
        val code = httpException.code()
        NetworkHttpError(errorMessage, code)
    }
}

private fun getErrorMessage(httpException: HttpException): String? {
    val moshi = Moshi.Builder().build()
    val errorAdapter = ErrorResponseJsonAdapter(moshi)
    val errorBody = httpException.response()?.errorBody()?.string()
    return errorBody?.let { errorJson ->
        try {
            val errorObject = errorAdapter.fromJson(errorJson)
            errorObject?.messageUpperCase ?: errorObject?.messageLowerCase
        } catch (e: JsonDataException) {
            Timber.d(e)
            null
        } catch (e: JsonEncodingException) {
            Timber.d(e)
            null
        } catch (e: EOFException) {
            Timber.d(e)
            null
        }
    }
}
