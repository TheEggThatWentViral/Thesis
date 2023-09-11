package com.example.thesisapp.data.disk.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.thesisapp.data.disk.dao.AdvertisedJobDao
import com.example.thesisapp.data.disk.dao.UserDao
import com.example.thesisapp.data.disk.model.RoomAdvertisedJob
import com.example.thesisapp.data.disk.model.RoomUser
import com.example.thesisapp.data.disk.util.Converters

@Database(
    exportSchema = false,
    version = 1,
    entities = [
        RoomUser::class,
        RoomAdvertisedJob::class
    ]
)
@TypeConverters(Converters::class)
abstract class InMemoryDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun advertisedJobDao(): AdvertisedJobDao
}
