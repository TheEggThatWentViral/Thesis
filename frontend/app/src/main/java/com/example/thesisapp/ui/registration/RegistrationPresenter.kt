package com.example.thesisapp.ui.registration

import com.example.thesisapp.domain.interactor.AuthenticationInteractor
import com.example.thesisapp.domain.model.RegistrationCredentials
import com.example.thesisapp.ui.util.PresentationResponse
import com.example.thesisapp.ui.util.makeNetworkCall
import javax.inject.Inject

class RegistrationPresenter @Inject constructor(
    private val authenticationInteractor: AuthenticationInteractor
) {

    suspend fun register(credentials: RegistrationCredentials): PresentationResponse<Boolean> = makeNetworkCall(
        interactor = { authenticationInteractor.register(credentials = credentials) },
        converter = { it }
    )
}