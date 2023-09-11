package com.example.thesisapp.data.disk.util

import androidx.room.TypeConverter
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
}
