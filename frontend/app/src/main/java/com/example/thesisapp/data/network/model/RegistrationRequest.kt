package com.example.thesisapp.data.network.model

import com.example.thesisapp.domain.model.AdvertisedJob
import com.example.thesisapp.domain.model.UserRole
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRequest(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val roles: Collection<UserRole>? = null,
    val jobsDone: Collection<AdvertisedJob>? = null,
    val workerRating: Int,
    val publisherRating: Int
)