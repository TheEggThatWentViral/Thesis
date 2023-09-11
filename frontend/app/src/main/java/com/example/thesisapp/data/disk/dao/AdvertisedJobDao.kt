package com.example.thesisapp.data.disk.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.thesisapp.data.disk.model.RoomAdvertisedJob
import com.example.thesisapp.data.disk.model.RoomJobState

@Dao
interface AdvertisedJobDao {

    @Query("SELECT * FROM jobs WHERE jobState = :jobState")
    fun getActiveJobs(jobState: RoomJobState = RoomJobState.ACTIVE): List<RoomAdvertisedJob>
}
