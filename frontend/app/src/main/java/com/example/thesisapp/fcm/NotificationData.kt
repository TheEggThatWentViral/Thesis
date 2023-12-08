package com.example.thesisapp.fcm

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationData(
    val title: String,
    val message: String
)