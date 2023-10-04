package com.example.webshopbackend.controller;

import com.example.webshopbackend.domain.AdvertisedJob;
import com.example.webshopbackend.domain.JobState;
import com.example.webshopbackend.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobService jobService;

    @GetMapping("/state")
    public ResponseEntity<?> getJobByState(@RequestBody JobState state) {
        List<AdvertisedJob> jobs = jobService.findJobByState(state);
        if (jobs == null) {
            log.error("The database doesn't contain any jobs with this state: {}", state);
            return  ResponseEntity.notFound().build();
        }
        log.info("Jobs with state {} were found in the database", state);
        return ResponseEntity.ok().body(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable("id") Long id) {
        AdvertisedJob job = jobService.getJob(id);
        if (job == null) {
            log.error("The database doesn't contain a job record with ID {}", id);
            return  ResponseEntity.notFound().build();
        }
        log.info("Job with ID {} was found in the database", id);
        return ResponseEntity.ok().body(job);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveJob(@RequestBody AdvertisedJob job) {
        if (jobService.getJob(job.getId()) != null) {
            log.error("Job with ID {} is already exist in the database", job.getId());
            return ResponseEntity.badRequest().build();
        }

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/api/jobs/save").toUriString()
        );

        return ResponseEntity.created(uri).body(jobService.saveJob(job));
    }
}
