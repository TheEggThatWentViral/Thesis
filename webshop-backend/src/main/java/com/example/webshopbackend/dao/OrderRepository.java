package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);
}
