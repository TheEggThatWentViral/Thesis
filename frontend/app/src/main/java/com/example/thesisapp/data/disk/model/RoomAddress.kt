package com.example.thesisapp.data.disk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class RoomAddress(
    @PrimaryKey
    val id: Long?,
    val country: String,
    val city: String,
    val zipCode: String,
    val street: String,
    val number: String
)
