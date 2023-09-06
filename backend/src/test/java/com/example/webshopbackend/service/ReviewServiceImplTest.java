package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.ReviewRepository;
import com.example.webshopbackend.dao.VehicleRepository;
import com.example.webshopbackend.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private VehicleRepository vehicleRepository;
    private ReviewServiceImpl reviewServiceImplTest;

    @BeforeEach
    void setup() {
        reviewServiceImplTest = new ReviewServiceImpl(reviewRepository, vehicleRepository);
    }

    @Test
    void saveReview() {
        //given
        Vehicle vehicle = new Vehicle(
                null,
                VehicleType.VAN,
                new BasicInfo(
                        null,
                        "Ford Transit",
                        10000000,
                        "Ford",
                        2016
                ),
                new InteriorInfo(
                        null,
                        3,
                        GearType.MANUAL,
                        6,
                        1500,
                        "grey"
                ),
                new ExteriorInfo(
                        null,
                        3150,
                        7.0,
                        4.2,
                        2,
                        "blue"
                ),
                new EngineInfo(
                        null,
                        EngineType.TRADITIONAL,
                        200,
                        FuelType.PETROL,
                        20.0,
                        WheelDrive.TWO_WHEEL_FRONT
                ),
                new ArrayList<>() {{
                    add(
                        new Review(
                            null,
                            7,
                            "Great for transporting things!",
                            "MekkElek01",
                            new GregorianCalendar(2017, Calendar.JUNE, 11).getTime()
                        )
                    );
                    add(
                        new Review(
                            null,
                            8,
                            "It helped me a lot when I was moving.",
                            "HiBob7",
                            new GregorianCalendar(2020, Calendar.DECEMBER, 24).getTime()
                        )
                    );
                }}
        );

        Review review = new Review(
            null,
            8,
            "It helped me a lot when I was moving.",
            "HiBob7",
            new GregorianCalendar(2020, Calendar.DECEMBER, 24).getTime()
        );

        Long id = 1L;

        given(vehicleRepository.findVehicleById(id))
                .willReturn(vehicle);

        //when
        reviewServiceImplTest.saveReview(id, review);

        //then
        ArgumentCaptor<Long> vehicleArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(vehicleRepository).findVehicleById(vehicleArgumentCaptor.capture());

        Long capturedId = vehicleArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);

        ArgumentCaptor<Review> reviewArgumentCaptor = ArgumentCaptor.forClass(Review.class);
        verify(reviewRepository).save(reviewArgumentCaptor.capture());

        Review capturedReview = reviewArgumentCaptor.getValue();
        assertThat(capturedReview).isEqualTo(review);
    }
}