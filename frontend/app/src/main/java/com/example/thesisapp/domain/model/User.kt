package com.example.thesisapp.domain.model

import com.example.thesisapp.data.disk.model.RoomUser
import com.example.thesisapp.data.network.model.UserResponse

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

fun RoomUser.toUser() {
    User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        password = password,
        roles = roles,
        jobsDone = jobsDone,
        workerRating = workerRating,
        publisherRating = publisherRating
    )
}

fun User.toRoomUser() {
    RoomUser(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        password = password,
        roles = roles,
        jobsDone = jobsDone,
        workerRating = workerRating,
        publisherRating = publisherRating
    )
}

fun UserResponse.toUser() {
    User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        password = password,
        roles = roles,
        jobsDone = jobsDone,
        workerRating = workerRating,
        publisherRating = publisherRating
    )
}

fun User.toUserResponse() {
    UserResponse(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        password = password,
        roles = roles,
        jobsDone = jobsDone,
        workerRating = workerRating,
        publisherRating = publisherRating
    )
}
