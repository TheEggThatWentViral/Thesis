package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepositoryTest;

    @Test
    void findOrderById() {
        //given
        Order order = new Order(
                null,
                1L,
                "Toyota Corolla",
                "Marcell Nagy",
                "nmarcell05@gmail.com"
        );

        orderRepositoryTest.save(order);

        //when
        Order orderFound = orderRepositoryTest.findOrderById(order.getId());

        //then
        assertThat(order).isEqualTo(orderFound);
    }
}