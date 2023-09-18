package com.example.thesisapp.data.disk.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thesisapp.domain.model.Address
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.domain.model.User
import com.squareup.moshi.Json

@Entity(tableName = "jobs")
data class RoomAdvertisedJob(
    @PrimaryKey
    val id: Long?,
    val title: String,
    val description: String,
    val address: Address,
    val price: String,
    val jobState: JobState,
    val publisher: User
)

enum class RoomJobState {
    @Json(name = "Active")
    ACTIVE,

    @Json(name = "Inactive")
    INACTIVE,

    @Json(name = "Done")
    DONE
}
