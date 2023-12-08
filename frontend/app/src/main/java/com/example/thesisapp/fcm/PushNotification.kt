package com.example.thesisapp.fcm

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PushNotification(
    val data: NotificationData,
    val to: String
)
