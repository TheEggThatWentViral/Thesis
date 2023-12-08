package com.example.thesisapp.domain.interactor

import com.example.thesisapp.data.network.model.JobResponse
import com.example.thesisapp.data.network.source.NotificationDataSource
import com.example.thesisapp.domain.model.toAdvertisedJob
import com.example.thesisapp.fcm.PushNotification
import com.example.thesisapp.util.NetworkNoResult
import com.example.thesisapp.util.NetworkResponse
import com.example.thesisapp.util.NetworkResult
import javax.inject.Inject

class NotificationInteractor @Inject constructor(
    private val notificationDataSource: NotificationDataSource
) {

    suspend fun applyForJob(notification: PushNotification): NetworkResponse<Boolean> {

        return when (val response = notificationDataSource.applyForJob(notification)) {
            is NetworkResult -> {
                NetworkResult(true)
            }
            is NetworkNoResult -> response
        }
    }
}