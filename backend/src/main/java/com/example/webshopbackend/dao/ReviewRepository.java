package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findReviewById(Long id);
}
