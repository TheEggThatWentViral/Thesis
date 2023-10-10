package com.example.thesisapp.ui.job.feed

import com.example.thesisapp.domain.model.AdvertisedJob
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

data class FeedViewState(
    val jobsNearYou: List<AdvertisedJob>,
    val jobsRecentlyAdded: List<AdvertisedJob>,
    val jobsHighlyPaid: List<AdvertisedJob>,
    val jobsAll: List<AdvertisedJob>,
    val loadingJobsError: StateEventWithContent<String> = consumed()
)