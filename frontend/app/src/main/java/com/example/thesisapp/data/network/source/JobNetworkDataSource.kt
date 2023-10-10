package com.example.thesisapp.data.network.source

import com.example.thesisapp.data.network.api.AuthenticationApi
import com.example.thesisapp.data.network.api.ThesisApi
import com.example.thesisapp.data.network.model.JobResponse
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.util.NetworkResponse
import com.example.thesisapp.util.apiCall

class JobNetworkDataSource(
    private val thesisApi: ThesisApi
) {

    suspend fun fetchJobsByState(state: JobState): NetworkResponse<List<JobResponse>> = apiCall {
        thesisApi.fetchJobsByState(state)
    }

    suspend fun fetchJobById(id: Long): NetworkResponse<JobResponse> = apiCall {
        thesisApi.fetchJobById(id)
    }

    suspend fun saveJob(job: JobResponse): NetworkResponse<JobResponse> = apiCall {
        thesisApi.saveJob(job)
    }
}