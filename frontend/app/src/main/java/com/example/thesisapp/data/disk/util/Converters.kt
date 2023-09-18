package com.example.thesisapp.data.disk.util

import androidx.room.TypeConverter
import com.example.thesisapp.data.disk.model.RoomAdvertisedJob
import com.example.thesisapp.data.disk.model.RoomJobState
import com.example.thesisapp.data.disk.model.RoomUser
import com.example.thesisapp.domain.model.Address
import com.example.thesisapp.domain.model.AdvertisedJob
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.domain.model.User
import com.example.thesisapp.domain.model.UserRole
import com.google.gson.Gson
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


class Converters {

    @TypeConverter
    fun offsetDateTimeToString(value: OffsetDateTime?): String {
        return value?.format(DateTimeFormatter.ISO_DATE_TIME) ?: ""
    }

    @TypeConverter
    fun stringToOffsetDateTime(value: String): OffsetDateTime? {
        if (value.isEmpty()) {
            return null
        }
        return OffsetDateTime.parse(value)
    }

    @TypeConverter
    fun userRoleListToString(list: Collection<UserRole>): String {
        var string = ""

        for (element in list) {
            string += Gson().toJson(element)
            string += "\n"
        }

        return string
    }

    @TypeConverter
    fun stringToUserRoleList(string: String): Collection<UserRole> {
        val collection: ArrayList<UserRole> = ArrayList()

        val strings = string.split("\n").toTypedArray()

        for (str in strings) {
            collection.add(Gson().fromJson(str, UserRole::class.java))
        }

        return collection
    }

    @TypeConverter
    fun jobListToString(list: Collection<AdvertisedJob>): String {
        var string = ""

        for (element in list) {
            string += Gson().toJson(element)
            string += "\n"
        }

        return string
    }

    @TypeConverter
    fun stringToJobList(string: String): Collection<AdvertisedJob> {
        val collection: ArrayList<AdvertisedJob> = ArrayList()

        val strings = string.split("\n").toTypedArray()

        for (str in strings) {
            collection.add(Gson().fromJson(str, AdvertisedJob::class.java))
        }

        return collection
    }

    @TypeConverter
    fun jobStateToInt(state: JobState): Int {
        return state.ordinal
    }

    @TypeConverter
    fun intToJobState(value: Int): JobState {
        return JobState.values()[value]
    }

    @TypeConverter
    fun userToString(user: User): String {
        return Gson().toJson(user)
    }

    @TypeConverter
    fun stringToUser(str: String): User {
        return Gson().fromJson(str, User::class.java)
    }

    @TypeConverter
    fun addressToString(address: Address): String {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun stringToAddress(str: String): Address {
        return Gson().fromJson(str, Address::class.java)
    }
}
