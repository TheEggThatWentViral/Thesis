package com.example.thesisapp.data.network.source

import com.example.thesisapp.data.network.api.AuthenticationApi
import com.example.thesisapp.data.network.model.LoginRequest
import com.example.thesisapp.data.network.model.LoginResponse
import com.example.thesisapp.data.network.model.RegistrationRequest
import com.example.thesisapp.data.network.model.RegistrationResponse
import com.example.thesisapp.domain.model.LoginCredentials
import com.example.thesisapp.domain.model.RegistrationCredentials
import com.example.thesisapp.util.NetworkResponse
import com.example.thesisapp.util.apiCall

class AuthenticationNetworkDataSource(
    private val authenticationApi: AuthenticationApi
) {

    suspend fun login(credentials: LoginCredentials): NetworkResponse<LoginResponse> = apiCall {
        authenticationApi.login(
            LoginRequest(
                username = credentials.username,
                password = credentials.password
            )
        )
    }
    suspend fun register(
        credentials: RegistrationCredentials
    ): NetworkResponse<RegistrationResponse> = apiCall {
        authenticationApi.register(
            RegistrationRequest(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                username = credentials.username,
                email = credentials.email,
                password = credentials.password
            )
        )
    }
}
