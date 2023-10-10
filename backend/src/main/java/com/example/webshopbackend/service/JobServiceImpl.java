package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.JobRepository;
import com.example.webshopbackend.domain.AdvertisedJob;
import com.example.webshopbackend.domain.JobState;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;


    @Override
    public AdvertisedJob getJob(Long id) {
        log.info("Fetching job with ID {}", id);
        return jobRepository.findAdvertisedJobById(id);
    }

    @Override
    public AdvertisedJob saveJob(AdvertisedJob job) {
        log.info("Saving new job with ID {} into database", job.getId());
        return jobRepository.save(job);
    }

    @Override
    public List<AdvertisedJob> findJobByState(JobState state) {
        log.info("Fetching jobs with state {}", state);
        return jobRepository.findAdvertisedJobByState(state);
    }
}
