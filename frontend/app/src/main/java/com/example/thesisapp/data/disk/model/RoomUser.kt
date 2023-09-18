package com.example.thesisapp.data.disk.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thesisapp.domain.model.AdvertisedJob
import com.example.thesisapp.domain.model.UserRole

@Entity(tableName = "users")
data class RoomUser(
    @PrimaryKey
    val id: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val roles: Collection<UserRole>,
    val jobsDone: Collection<AdvertisedJob>,
    val workerRating: Int,
    val publisherRating: Int
)
