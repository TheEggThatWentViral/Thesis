package com.example.thesisapp.domain.model

import com.example.thesisapp.data.disk.model.RoomAdvertisedJob
import com.example.thesisapp.data.network.model.JobResponse

data class AdvertisedJob(
    val id: Long? = null,
    val title: String,
    val description: String,
    val address: Address,
    val coordinates: Coordinates,
    val price: Int,
    val jobState: JobState = JobState.ACTIVE,
    val publisher: User? = null,
    val imageUrl: String,
    val time: String,
    val newMessage: Boolean = false
)

fun JobResponse.toAdvertisedJob() =
    AdvertisedJob(
        id = id,
        title = title,
        description = description,
        address = address,
        coordinates = coordinates,
        price = price,
        jobState = jobState,
        publisher = publisher,
        imageUrl = imageUrl,
        time = time,
        newMessage = newMessage
    )

fun AdvertisedJob.toJobResponse() =
    JobResponse(
        id = id,
        title = title,
        description = description,
        address = address,
        coordinates = coordinates,
        price = price,
        jobState = jobState,
        publisher = publisher,
        imageUrl = imageUrl,
        time = time,
        newMessage = newMessage
    )

fun RoomAdvertisedJob.toAdvertisedJob() =
    AdvertisedJob(
        id = id,
        title = title,
        description = description,
        address = address,
        coordinates = coordinates,
        price = price,
        jobState = jobState,
        publisher = publisher,
        imageUrl = imageUrl,
        time = time,
        newMessage = newMessage
    )

fun AdvertisedJob.toRoomAdvertisedJob() =
    RoomAdvertisedJob(
        id = id,
        title = title,
        description = description,
        address = address,
        coordinates = coordinates,
        price = price,
        jobState = jobState,
        publisher = publisher,
        imageUrl = imageUrl,
        time = time,
        newMessage = newMessage
    )
