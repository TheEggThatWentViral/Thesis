package com.example.thesisapp.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "Message") val messageUpperCase: String?,
    @Json(name = "message") val messageLowerCase: String?,
)
