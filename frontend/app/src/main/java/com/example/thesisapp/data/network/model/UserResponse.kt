package com.example.thesisapp.data.network.model

import com.example.thesisapp.domain.model.AdvertisedJob
import com.example.thesisapp.domain.model.UserRole
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val roles: Collection<UserRole> = ArrayList(),
    val jobsDone: Collection<AdvertisedJob> = ArrayList(),
    val workerRating: Float,
    val publisherRating: Float,
    val profilePicture: String
)
