package com.example.thesisapp.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationResponse(
    val messages: ArrayList<String>
)
