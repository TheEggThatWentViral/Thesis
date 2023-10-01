package com.example.thesisapp.domain.model

data class RegistrationCredentials(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val workerRating: Int = 0,
    val publisherRating: Int = 0
)
