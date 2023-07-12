package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.OrderRepository;
import com.example.webshopbackend.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final JmsTemplate jmsTemplate;

    @Override
    public Order saveOrder(Order order) {
        log.info("Saving order with ID {} into the database", order.getId());
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        log.info("Fetching order with ID {}", id);
        return orderRepository.findOrderById(id);
    }

    @Override
    public void sendOrder(Order order) {
        log.info("Sending order to car salon");
        jmsTemplate.convertAndSend("orderQueue", order.toString());
    }
}
