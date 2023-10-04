package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.AdvertisedJob;
import com.example.webshopbackend.domain.JobState;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<AdvertisedJob, Long> {
    AdvertisedJob findAdvertisedJobById(Long id);
    List<AdvertisedJob> findAdvertisedJobByState(JobState state);
}
