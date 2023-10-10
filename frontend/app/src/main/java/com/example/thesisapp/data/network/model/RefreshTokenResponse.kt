package com.example.thesisapp.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenResponse(
    val refresh_token: String,
    val role: List<String>
)
