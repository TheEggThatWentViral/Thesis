package com.example.thesisapp.ui.job.feed

import com.example.thesisapp.domain.interactor.JobInteractor
import com.example.thesisapp.domain.model.AdvertisedJob
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.ui.util.PresentationResponse
import com.example.thesisapp.ui.util.makeNetworkCall
import javax.inject.Inject

class FeedPresenter @Inject constructor(
    private val jobInteractor: JobInteractor
) {

    suspend fun getJobsByState(state: JobState): PresentationResponse<List<FeedJobDataPreview>> =
        makeNetworkCall(
            interactor = {
                jobInteractor.getJobsByState(state)
            },
            converter = { it.map(AdvertisedJob::toFeedJobDataPreview) }
        )

    suspend fun getJobById(id: Long): PresentationResponse<AdvertisedJob> = makeNetworkCall(
        interactor = {
            jobInteractor.getJobById(id)
        },
        converter = { it }
    )

    suspend fun saveJob(job: AdvertisedJob): PresentationResponse<Boolean> = makeNetworkCall(
        interactor = {
            jobInteractor.saveJob(job)
        },
        converter = { it }
    )
}

data class FeedJobDataPreview(
    val jobName: String,
    val price: Int
)

fun AdvertisedJob.toFeedJobDataPreview(): FeedJobDataPreview {
    return FeedJobDataPreview(
        jobName = title,
        price = price
    )
}