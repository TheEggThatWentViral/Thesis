package com.example.thesisapp.ui.job.feed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesisapp.R
import com.example.thesisapp.domain.model.JobState
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
class FeedViewModel @Inject constructor(
    private val feedPresenter: FeedPresenter
) : ViewModel() {

    private val _stateStream: MutableStateFlow<FeedViewState> =
        MutableStateFlow(
            FeedViewState(listOf(), listOf(), listOf(), listOf())
        )
    val stateStream = _stateStream.asStateFlow()

    private var state: FeedViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }

    fun getJobsByState(context: Context) {
        viewModelScope.launch {
            when (
                val response = feedPresenter.getJobsByState(
                    JobState.ACTIVE
                )
            ) {
                is PresentationResult -> {
                    Timber.i("Jobs are ready to be loaded")
                    state = state.copy(
                        jobsHighlyPaid = response.result,
                        jobsNearYou = response.result,
                        jobsRecentlyAdded = response.result,
                        jobsAll = response.result
                    )
                }
                is PresentationHttpError -> {
                    Timber.i(
                        "Http error occurred during loading jobs"
                    )
                    state = state.copy(
                        loadingJobsError =
                        triggered(
                            response.message ?: context.getString(R.string.unknown_error_msg)
                        )
                    )
                }
                else -> {
                    Timber.i("Unknown error occurred during loading jobs")
                    state = state.copy(
                        loadingJobsError =
                        triggered(context.getString(R.string.unknown_error_msg))
                    )
                }
            }
        }
    }
}
