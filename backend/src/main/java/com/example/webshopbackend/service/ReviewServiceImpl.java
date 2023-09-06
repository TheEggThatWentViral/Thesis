package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.ReviewRepository;
import com.example.webshopbackend.dao.VehicleRepository;
import com.example.webshopbackend.domain.Review;
import com.example.webshopbackend.domain.Vehicle;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public void saveReview(Long id, Review review) {
        log.info("Fetching vehicle with ID {}", id);
        Vehicle vehicle = vehicleRepository.findVehicleById(id);

        log.info("Saving review with ID {} for vehicle with ID {}", review.getId(), vehicle.getId());
        Review savedReview = reviewRepository.save(review);
        vehicle.getReviews().add(savedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findReviewById(id);

        log.info("Deleting review with ID {}", review);
        reviewRepository.delete(review);
    }
}
