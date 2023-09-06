package com.example.webshopbackend.service;

import com.example.webshopbackend.domain.Checkout;
import com.example.webshopbackend.domain.User;

public interface CheckoutService {
    Checkout saveCheckout(Checkout checkout);
    Checkout getCheckout(Long id);
    void deleteCheckout(Long id);
}
