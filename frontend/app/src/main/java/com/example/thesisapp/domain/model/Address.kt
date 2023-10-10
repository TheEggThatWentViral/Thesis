package com.example.thesisapp.domain.model

import com.example.thesisapp.data.disk.model.RoomAddress

class Address(
    val id: Long? = null,
    val country: String,
    val city: String,
    val zipCode: String,
    val street: String,
    val number: String
) {
    override fun toString(): String {
        return "$zipCode $city, $street $number"
    }
}



fun RoomAddress.toAddress() {
    Address(
        id = id,
        country = country,
        city = city,
        zipCode = zipCode,
        street = street,
        number = number
    )
}

fun Address.toRoomAddress() {
    RoomAddress(
        id = id,
        country = country,
        city = city,
        zipCode = zipCode,
        street = street,
        number = number
    )
}
