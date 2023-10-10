package com.example.thesisapp.data.disk.source

import com.example.thesisapp.data.disk.dao.AdvertisedJobDao
import com.example.thesisapp.data.disk.model.RoomAdvertisedJob
import com.example.thesisapp.domain.model.JobState

class JobDiskSataSource(
    private val advertisedJobDao: AdvertisedJobDao
) {

    fun getJobsByState(state: JobState) = advertisedJobDao.getJobsByState(state)

    fun getJobById(id: Long) = advertisedJobDao.getJobById(id)

    fun saveJob(job: RoomAdvertisedJob) {
        advertisedJobDao.saveJob(job)
    }
}
