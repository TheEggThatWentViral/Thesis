package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Checkout findCheckoutById(Long id);
}
