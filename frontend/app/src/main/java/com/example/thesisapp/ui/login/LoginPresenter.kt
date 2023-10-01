package com.example.thesisapp.ui.login

import com.example.thesisapp.domain.interactor.AuthenticationInteractor
import com.example.thesisapp.domain.model.LoginCredentials
import com.example.thesisapp.ui.util.PresentationResponse
import com.example.thesisapp.ui.util.makeNetworkCall
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val authenticationInteractor: AuthenticationInteractor
) {
    suspend fun loginWithCredentials(
        credentials: LoginCredentials,
        rememberMe: Boolean,
    ): PresentationResponse<Boolean> = makeNetworkCall(
        interactor = {
            authenticationInteractor.loginWithCredentials(
                credentials = credentials,
                rememberMe = rememberMe
            )
        },
        converter = { it }
    )

    suspend fun loginWithSavedCredentials(): PresentationResponse<Boolean> = makeNetworkCall(
        interactor = {
            authenticationInteractor.loginUsingSavedCredentials()
        },
        converter = { it }
    )
}