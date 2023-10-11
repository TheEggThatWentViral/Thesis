package com.example.thesisapp.data.network.model

import com.example.thesisapp.domain.model.Address
import com.example.thesisapp.domain.model.Coordinates
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.domain.model.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobResponse(
    val id: Long? = null,
    val title: String,
    val description: String,
    val address: Address,
    val coordinates: Coordinates,
    val price: Int,
    val jobState: JobState,
    val publisher: User? = null,
    val imageUrl: String,
    val time: String,
    val newMessage: Boolean
)
