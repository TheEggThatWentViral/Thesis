package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VehicleRepositoryTest {

    @Autowired
    VehicleRepository vehicleRepositoryTest;

    private final Vehicle vehicle1 = new Vehicle(
            null,
            VehicleType.CAR,
            new BasicInfo(
                    null,
                    "Toyota Corolla",
                    5000000,
                    "Toyota",
                    2014
            ),
            new InteriorInfo(
                    null,
                    5,
                    GearType.MANUAL,
                    5,
                    250,
                    "kaki"
            ),
            new ExteriorInfo(
                    null,
                    1200,
                    6.2,
                    3.5,
                    4,
                    "red"
            ),
            new EngineInfo(
                    null,
                    EngineType.TRADITIONAL,
                    110,
                    FuelType.DIESEL,
                    10.0,
                    WheelDrive.TWO_WHEEL_FRONT
            ),
            Arrays.asList(
                    new Review(
                            null,
                            5,
                            "It's not a luxury car but it is a good deal for the price.",
                            "JoskaBacsi64",
                            new GregorianCalendar(2018, Calendar.MARCH, 20).getTime()
                    ),
                    new Review(
                            null,
                            6,
                            "It's alright, feels good to drive.",
                            "VinDiesel999",
                            new GregorianCalendar(2020, Calendar.NOVEMBER, 3).getTime()
                    )
            )
    );

    private final Vehicle vehicle2 = new Vehicle(
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
            Arrays.asList(
                    new Review(
                            null,
                            7,
                            "Great for transporting things!",
                            "MekkElek01",
                            new GregorianCalendar(2017, Calendar.JUNE, 11).getTime()
                    ),
                    new Review(
                            null,
                            8,
                            "It helped me a lot when I was moving.",
                            "HiBob7",
                            new GregorianCalendar(2020, Calendar.DECEMBER, 24).getTime()
                    )
            )
    );

    @BeforeEach
    void setup() {
        vehicleRepositoryTest.save(vehicle1);
        vehicleRepositoryTest.save(vehicle2);
    }

    @Test
    void findVehicleById() {
        //when
        Vehicle vehiclesFound = vehicleRepositoryTest.findVehicleById(vehicle1.getId());

        //then
        assertThat(vehicle1).isIn(vehiclesFound);
        assertThat(vehicle2).isNotIn(vehiclesFound);
    }

    @Test
    void findVehicleByVehicleType() {
        //when
        List<Vehicle> vehiclesFound = vehicleRepositoryTest.findVehicleByVehicleType(vehicle1.getVehicleType());

        //then
        assertThat(vehicle1).isIn(vehiclesFound);
        assertThat(vehicle2).isNotIn(vehiclesFound);
    }

    @Test
    void findVehicleByBasicInfo() {
        //when
        Vehicle vehicleFound = vehicleRepositoryTest.findVehicleByBasicInfo(vehicle1.getBasicInfo());

        //then
        assertThat(vehicle1).isEqualTo(vehicleFound);
    }

    @Test
    void findVehicleByInteriorInfo() {
        //when
        Vehicle vehicleFound = vehicleRepositoryTest.findVehicleByInteriorInfo(vehicle1.getInteriorInfo());

        //then
        assertThat(vehicle1).isEqualTo(vehicleFound);
    }

    @Test
    void findVehicleByExteriorInfo() {
        //when
        Vehicle vehicleFound = vehicleRepositoryTest.findVehicleByExteriorInfo(vehicle1.getExteriorInfo());

        //then
        assertThat(vehicle1).isEqualTo(vehicleFound);
    }

    @Test
    void findVehicleByEngineInfo() {
        //when
        Vehicle vehicleFound = vehicleRepositoryTest.findVehicleByEngineInfo(vehicle1.getEngineInfo());

        //then
        assertThat(vehicle1).isEqualTo(vehicleFound);
    }
}