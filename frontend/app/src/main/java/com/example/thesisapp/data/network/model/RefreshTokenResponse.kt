package com.example.thesisapp.data.network.model

data class RefreshTokenResponse(
    val refresh_token: String,
    val role: List<String>
)
