package com.example.thesisapp.data.network.api

import com.example.thesisapp.data.network.model.JobResponse
import com.example.thesisapp.domain.model.JobState
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ThesisApi {

    @GET
    suspend fun fetchJobsByState(
        @Body state: JobState
    ): List<JobResponse>

    @GET
    suspend fun fetchJobById(
        @Path("id") id: Long
    ): JobResponse

    @POST
    suspend fun saveJob(job: JobResponse): JobResponse
}
