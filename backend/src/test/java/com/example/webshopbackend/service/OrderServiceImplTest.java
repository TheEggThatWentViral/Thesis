package com.example.webshopbackend.service;

import com.example.webshopbackend.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private JmsTemplate jmsTemplate;
    private OrderServiceImpl orderServiceImplTest;

    @BeforeEach
    void setup() {
        orderServiceImplTest = new OrderServiceImpl(
                orderRepository,
                jmsTemplate
        );
    }

    @Test
    void saveOrder() {
        //given
        Order order = new Order(
                null,
                1L,
                "Toyota Corolla",
                "Marcell Nagy",
                "nmarcell05@gmail.com"
        );

        //when
        orderServiceImplTest.saveOrder(order);

        //then
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderArgumentCaptor.capture());

        Order capturedOrder = orderArgumentCaptor.getValue();
        assertThat(capturedOrder).isEqualTo(order);
    }

    @Test
    void getOrder() {
        //given
        Long id = 1L;

        //when
        orderServiceImplTest.getOrder(id);

        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(orderRepository).findOrderById(idArgumentCaptor.capture());

        Long capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void sendOrder() {
        //given
        Order order = new Order(
                null,
                1L,
                "Toyota Corolla",
                "Marcell Nagy",
                "nmarcell05@gmail.com"
        );

        String queue = "orderQueue";

        //when
        orderServiceImplTest.sendOrder(order);

        //then
        ArgumentCaptor<String> queueArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> orderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(jmsTemplate).convertAndSend(
                queueArgumentCaptor.capture(),
                orderArgumentCaptor.capture()
        );

        String capturedQueue = queueArgumentCaptor.getValue();
        assertThat(capturedQueue).isEqualTo(queue);

        String capturedOrder = orderArgumentCaptor.getValue();
        assertThat(capturedOrder).isEqualTo(order.toString());
    }
}