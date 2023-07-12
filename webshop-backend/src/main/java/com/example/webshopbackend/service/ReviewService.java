package com.example.webshopbackend.service;

import com.example.webshopbackend.domain.Review;

public interface ReviewService {
    void saveReview(Long id, Review review);
    void deleteReview(Long id);
}
