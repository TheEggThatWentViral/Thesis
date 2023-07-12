package com.example.webshopbackend.service;

import com.example.webshopbackend.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
    Order saveOrder(Order order);
    Order getOrder(Long id);
    void sendOrder(Order order) throws JsonProcessingException;
}
