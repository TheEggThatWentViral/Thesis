package com.example.thesisapp.domain.model

data class AdvertisedJob(
    val id: Long? = null,
    val title: String,
    val description: String,
    val address: Address,
    val price: String,
    val jobState: JobState,
    val publisher: User
)
