package com.example.thesisapp.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val access_token: String,
    val refresh_token: String,
    val role: String
)
