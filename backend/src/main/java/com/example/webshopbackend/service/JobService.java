package com.example.webshopbackend.service;

import com.example.webshopbackend.domain.AdvertisedJob;
import com.example.webshopbackend.domain.JobState;
import java.util.List;

public interface JobService {
    AdvertisedJob getJob(Long id);
    AdvertisedJob saveJob(AdvertisedJob job);
    List<AdvertisedJob> findJobByState(JobState state);
}
