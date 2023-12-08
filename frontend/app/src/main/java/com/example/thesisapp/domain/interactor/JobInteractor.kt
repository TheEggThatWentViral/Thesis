package com.example.thesisapp.domain.interactor

import com.example.thesisapp.data.disk.model.RoomAdvertisedJob
import com.example.thesisapp.data.disk.source.JobDiskSataSource
import com.example.thesisapp.data.network.model.JobResponse
import com.example.thesisapp.data.network.source.JobNetworkDataSource
import com.example.thesisapp.domain.model.AdvertisedJob
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.domain.model.toAdvertisedJob
import com.example.thesisapp.domain.model.toJobResponse
import com.example.thesisapp.util.NetworkNoResult
import com.example.thesisapp.util.NetworkResponse
import com.example.thesisapp.util.NetworkResult
import javax.inject.Inject

class JobInteractor @Inject constructor(
    private val jobDiskSataSource: JobDiskSataSource,
    private val jobNetworkDataSource: JobNetworkDataSource
) {

    suspend fun getJobsByState(
        state: JobState
    ): NetworkResponse<List<AdvertisedJob>> {

        val savedData = jobDiskSataSource.getJobsByState(state)
        if (savedData.isNotEmpty()) {
            return NetworkResult(
                savedData.map(RoomAdvertisedJob::toAdvertisedJob)
            )
        }

        return when (
            val response = jobNetworkDataSource.fetchJobsByState(state)
        ) {
            is NetworkResult -> {
                NetworkResult(
                    response.result.map(JobResponse::toAdvertisedJob)
                )
            }

            is NetworkNoResult -> response
        }
    }

    suspend fun getJobById(id: Long): NetworkResponse<AdvertisedJob> {
        val savedData = jobDiskSataSource.getJobById(id)
        if (savedData != null) {
            return NetworkResult(savedData.toAdvertisedJob())
        }

        return when (val response = jobNetworkDataSource.fetchJobById(id)) {
            is NetworkResult -> {
                NetworkResult(response.result.toAdvertisedJob())
            }

            is NetworkNoResult -> response
        }
    }

    suspend fun saveJob(job: AdvertisedJob): NetworkResponse<Boolean> {
        return when (val response = jobNetworkDataSource.saveJob(job.toJobResponse())) {
            is NetworkResult -> NetworkResult(true)
            is NetworkNoResult -> NetworkResult(false)
        }
    }
}