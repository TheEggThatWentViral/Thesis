package com.example.thesisapp.ui.util

import com.example.thesisapp.util.NetworkHttpError
import com.example.thesisapp.util.NetworkIOError
import com.example.thesisapp.util.NetworkResponse
import com.example.thesisapp.util.NetworkResult
import com.example.thesisapp.util.UnknownHostError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *                          PresentationResult<T>
 *                         /
 *                        /
 *  PresentationResponse<T>                       PresentationHttpError
 *                        \                      /   PresentationThrottlingError
 *                         \                    /  /
 *                          PresentationNoResult -<
 *                                              \  \
 *                                               \  PresentationWrongServiceError
 *                                                PresentationNetworkError
 */
sealed class PresentationResponse<out T : Any>

class PresentationResult<out T : Any>(val result: T) : PresentationResponse<T>()

sealed class PresentationNoResult : PresentationResponse<Nothing>()

data object PresentationNetworkError : PresentationNoResult()

data object PresentationWrongServiceError : PresentationNoResult()

data class PresentationHttpError(
    val message: String?,
    val code: Int? = null
) : PresentationNoResult()

/**
 * Makes a network call via the [interactor] and expects a network model ([NM]) as response and converts it
 * into a presentation model ([PM]) with the [converter].
 *
 * @param NM the network model.
 * @param PM the presentation model.
 * @param interactor the way to interact with the network.
 * @param converter the way the network data can be converted into presentation data.
 *
 * @return a presentation model wrapped into [PresentationResponse].
 */
suspend inline fun <NM : Any, PM : Any> makeNetworkCall(
    crossinline interactor: suspend CoroutineScope.() -> NetworkResponse<NM>,
    crossinline converter: (suspend CoroutineScope.(NM) -> PM)
): PresentationResponse<PM> = withContext(Dispatchers.IO) {
    when (val networkResponse = interactor()) {
        is NetworkResult -> PresentationResult(converter(networkResponse.result))
        is NetworkHttpError -> PresentationHttpError(
            networkResponse.errorMessage,
            networkResponse.code
        )
        is NetworkIOError -> PresentationNetworkError
        is UnknownHostError -> PresentationWrongServiceError
    }
}

/**
 * Makes a network call via the [interactor].
 *
 * @param interactor the way to interact with the network.
 *
 * @return a [PresentationResponse].
 */
suspend fun makeNetworkCall(
    interactor: suspend CoroutineScope.() -> NetworkResponse<Unit>
): PresentationResponse<Unit> = makeNetworkCall(interactor, { })
