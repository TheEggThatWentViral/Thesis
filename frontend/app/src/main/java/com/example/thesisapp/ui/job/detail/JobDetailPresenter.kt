package com.example.thesisapp.ui.job.detail

import com.example.thesisapp.domain.interactor.NotificationInteractor
import com.example.thesisapp.fcm.PushNotification
import com.example.thesisapp.ui.util.PresentationResponse
import com.example.thesisapp.ui.util.makeNetworkCall
import javax.inject.Inject

class JobDetailPresenter @Inject constructor(
    private val notificationInteractor: NotificationInteractor
) {

    suspend fun applyForJob(notification: PushNotification): PresentationResponse<Boolean> =
        makeNetworkCall(
            interactor = { notificationInteractor.applyForJob(notification) },
            converter = { it }
        )
}