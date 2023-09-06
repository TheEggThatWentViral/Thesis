package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CheckoutRepositoryTest {

    @Autowired
    CheckoutRepository checkoutRepositoryTest;

    @Test
    void findCheckoutById() {
        //given
        Vehicle vehicle = new Vehicle(
                null,
                VehicleType.MOTORCYCLE,
                new BasicInfo(
                        null,
                        "Vespa",
                        1000000,
                        "Vespa",
                        2018
                ),
                new InteriorInfo(
                        null,
                        2,
                        GearType.AUTOMATIC,
                        0,
                        10,
                        "dirty white"
                ),
                new ExteriorInfo(
                        null,
                        100,
                        3.0,
                        0.8,
                        0,
                        "cyan"
                ),
                new EngineInfo(
                        null,
                        EngineType.ELECTRIC,
                        60,
                        FuelType.ELECTRIC_POWER,
                        10.0,
                        WheelDrive.TWO_WHEEL_BACK
                ),
                Arrays.asList(
                        new Review(
                                null,
                                4,
                                "It's really weak, and too slow for me...",
                                "AbdrakaDaniel",
                                new GregorianCalendar(2021, Calendar.JANUARY, 17).getTime()
                        ),
                        new Review(
                                null,
                                9,
                                "I really love it, so smooth!",
                                "SamuraiJack11",
                                new GregorianCalendar(2022, Calendar.AUGUST, 30).getTime()
                        )
                )
        );

        Address address = new Address(
                null,
                "Hungary",
                "zalaegerszeg",
                "2341",
                "Petőfu utca",
                "10."
        );

        Checkout checkout = new Checkout(
                null,
                vehicle,
                address,
                "Marcell",
                "Nagy"
        );

        checkoutRepositoryTest.save(checkout);

        //when
        Checkout checkoutFound = checkoutRepositoryTest.findCheckoutById(4L);

        //then
        assertThat(checkout).isEqualTo(checkoutFound);
    }
}