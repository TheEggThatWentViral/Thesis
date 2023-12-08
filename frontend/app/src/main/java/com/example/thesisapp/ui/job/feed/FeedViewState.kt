package com.example.thesisapp.ui.job.feed

import com.example.thesisapp.domain.model.AdvertisedJob
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

data class FeedViewState(
    val jobsNearYou: List<FeedJobDataPreview>,
    val jobsRecentlyAdded: List<FeedJobDataPreview>,
    val jobsHighlyPaid: List<FeedJobDataPreview>,
    val jobsAll: List<FeedJobDataPreview>,
    val loadingJobsError: StateEventWithContent<String> =
        consumed()
)