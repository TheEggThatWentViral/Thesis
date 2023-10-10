package com.example.thesisapp.data.disk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class RoomCoordinates(
    @PrimaryKey
    val id: Long,
    val longitude: Long,
    val latitude: Long
)
