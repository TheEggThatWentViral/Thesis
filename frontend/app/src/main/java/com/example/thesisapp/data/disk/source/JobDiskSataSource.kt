package com.example.thesisapp.data.disk.source

import com.example.thesisapp.data.disk.dao.AdvertisedJobDao

class JobDiskSataSource(
    private val advertisedJobDao: AdvertisedJobDao
) {

    fun getActivateJobs() = advertisedJobDao.getActiveJobs()
}
