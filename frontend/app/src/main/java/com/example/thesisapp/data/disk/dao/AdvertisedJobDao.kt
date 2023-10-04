package com.example.thesisapp.data.disk.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thesisapp.data.disk.model.RoomAdvertisedJob
import com.example.thesisapp.domain.model.JobState

@Dao
interface AdvertisedJobDao {

    @Query("SELECT * FROM jobs WHERE jobState = :jobState")
    fun getJobsByState(jobState: JobState): List<RoomAdvertisedJob>

    @Query("SELECT * FROM jobs WHERE id = :id")
    fun getJobById(id: Long): RoomAdvertisedJob?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveJob(job: RoomAdvertisedJob)
}
