package com.example.thesisapp.domain.model

data class Address(
    val id: Long? = null,
    val country: String,
    val city: String,
    val zipCode: String,
    val street: String,
    val number: String
)
