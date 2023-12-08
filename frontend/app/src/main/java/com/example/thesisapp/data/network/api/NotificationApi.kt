package com.example.thesisapp.data.network.api

import com.example.thesisapp.BuildConfig
import com.example.thesisapp.data.network.model.NotificationResponse
import com.example.thesisapp.fcm.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers(
        "Authorization: key=${BuildConfig.SERVER_KEY}",
        "Content-Type: ${BuildConfig.FCM_CONTENT_TYPE}"
    )
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): NotificationResponse
}