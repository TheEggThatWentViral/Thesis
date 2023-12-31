package com.example.thesisapp.data.network.api

import com.example.thesisapp.data.network.model.RegistrationRequest
import com.example.thesisapp.data.network.model.LoginRequest
import com.example.thesisapp.data.network.model.LoginResponse
import com.example.thesisapp.data.network.model.RegistrationResponse
import com.example.thesisapp.data.network.model.RefreshTokenResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @POST("register")
    suspend fun register(
        @Body registrationRequest: RegistrationRequest
    ): RegistrationResponse

    @GET("refresh_token")
    suspend fun requestNewToken(
        @Header("Authorization") refreshToken: String
    ): RefreshTokenResponse
}
