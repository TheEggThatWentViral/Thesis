package com.example.thesisapp.domain.model

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val roles: Collection<UserRole> = ArrayList(),
    val jobsDone: Collection<AdvertisedJob> = ArrayList(),
    val workerRating: Int,
    val publisherRating: Int
)
