package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.EngineInfo;
import com.example.webshopbackend.domain.EngineType;
import com.example.webshopbackend.domain.FuelType;
import com.example.webshopbackend.domain.WheelDrive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EngineInfoRepositoryTest {

    @Autowired
    EngineInfoRepository engineInfoRepositoryTest;

    @Test
    void findByEngineType() {
        //given
        EngineInfo engineInfo1 =  new EngineInfo(
                null,
                EngineType.ELECTRIC,
                60,
                FuelType.ELECTRIC_POWER,
                10.0,
                WheelDrive.TWO_WHEEL_BACK
        );

        EngineInfo engineInfo2 = new EngineInfo(
                null,
                EngineType.TRADITIONAL,
                110,
                FuelType.DIESEL,
                10.0,
                WheelDrive.TWO_WHEEL_FRONT
        );

        engineInfoRepositoryTest.save(engineInfo1);
        engineInfoRepositoryTest.save(engineInfo2);

        String engineType = EngineType.TRADITIONAL;

        //when
        List<EngineInfo> engineInfosFound = engineInfoRepositoryTest.findByEngineType(engineType);

        //then
        assertThat(engineInfo1).isIn(engineInfosFound);
        assertThat(engineInfo2).isNotIn(engineInfosFound);
    }

    @Test
    void findByFuelType() {
        //given
        EngineInfo engineInfo1 =  new EngineInfo(
                null,
                EngineType.ELECTRIC,
                60,
                FuelType.ELECTRIC_POWER,
                10.0,
                WheelDrive.TWO_WHEEL_BACK
        );

        EngineInfo engineInfo2 = new EngineInfo(
                null,
                EngineType.TRADITIONAL,
                110,
                FuelType.DIESEL,
                10.0,
                WheelDrive.TWO_WHEEL_FRONT
        );

        engineInfoRepositoryTest.save(engineInfo1);
        engineInfoRepositoryTest.save(engineInfo2);

        String fuelType = FuelType.DIESEL;

        //when
        List<EngineInfo> engineInfosFound = engineInfoRepositoryTest.findByFuelType(fuelType);

        //then
        assertThat(engineInfo1).isNotIn(engineInfosFound);
        assertThat(engineInfo2).isIn(engineInfosFound);
    }

    @Test
    void findByWheelDrive() {
        //given
        EngineInfo engineInfo1 =  new EngineInfo(
                null,
                EngineType.ELECTRIC,
                60,
                FuelType.ELECTRIC_POWER,
                10.0,
                WheelDrive.TWO_WHEEL_BACK
        );

        EngineInfo engineInfo2 = new EngineInfo(
                null,
                EngineType.TRADITIONAL,
                110,
                FuelType.DIESEL,
                10.0,
                WheelDrive.TWO_WHEEL_FRONT
        );

        engineInfoRepositoryTest.save(engineInfo1);
        engineInfoRepositoryTest.save(engineInfo2);

        WheelDrive wheelDrive = WheelDrive.TWO_WHEEL_BACK;

        //when
        List<EngineInfo> engineInfosFound = engineInfoRepositoryTest.findByWheelDrive(wheelDrive);

        //then
        assertThat(engineInfo1).isIn(engineInfosFound);
        assertThat(engineInfo2).isNotIn(engineInfosFound);
    }
}