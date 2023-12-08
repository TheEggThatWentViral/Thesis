package com.example.thesisapp.ui.job.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesisapp.R
import com.example.thesisapp.fcm.NotificationData
import com.example.thesisapp.fcm.PushNotification
import com.example.thesisapp.ui.util.PresentationHttpError
import com.example.thesisapp.ui.util.PresentationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.triggered
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class JobDetailViewModel @Inject constructor(
    private val jobDetailPresenter: JobDetailPresenter
) : ViewModel() {

    private val _stateStream: MutableStateFlow<JobDetailViewState> =
        MutableStateFlow(JobDetailViewState())
    val stateStream = _stateStream.asStateFlow()

    private var state: JobDetailViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }

    fun applyForJob(context: Context) {
        viewModelScope.launch {

            val response = jobDetailPresenter.applyForJob(
                PushNotification(
                    data = NotificationData(
                        title = "Cleaning",
                        message = "New application for your job!"
                    ),
                    "/topics/new-application"
                )
            )

            when (response) {
                is PresentationResult -> {
                    state = if (response.result) {
                        Timber.i("Application is successful")
                        state.copy(applicationSuccess = triggered)
                    } else {
                        state.copy(
                            applicationError = triggered(context.getString(R.string.unknown_error_msg))
                        )
                    }
                }

                is PresentationHttpError -> {
                    Timber.i("Application http error")
                    state = state.copy(
                        applicationError = triggered(
                            response.message ?: context.getString(R.string.unknown_error_msg)
                        )
                    )
                }

                else -> {
                    Timber.i("Application network error")
                    state = state.copy(
                        applicationError = triggered(context.getString(R.string.unknown_error_msg))
                    )
                }
            }
        }
    }
}