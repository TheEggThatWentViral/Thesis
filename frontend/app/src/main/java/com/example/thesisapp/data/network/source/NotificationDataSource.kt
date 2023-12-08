package com.example.thesisapp.data.network.source

import com.example.thesisapp.data.network.api.NotificationApi
import com.example.thesisapp.data.network.model.NotificationResponse
import com.example.thesisapp.fcm.PushNotification
import com.example.thesisapp.util.NetworkResponse
import com.example.thesisapp.util.apiCall

class NotificationDataSource(
    private val notificationApi: NotificationApi
) {

    suspend fun applyForJob(
        notification: PushNotification
    ): NetworkResponse<NotificationResponse> =
        apiCall {
            notificationApi.postNotification(notification)
        }
}