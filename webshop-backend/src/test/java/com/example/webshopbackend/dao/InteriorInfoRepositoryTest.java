package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.GearType;
import com.example.webshopbackend.domain.InteriorInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InteriorInfoRepositoryTest {

    @Autowired
    InteriorInfoRepository interiorInfoRepositoryTest;

    @Test
    void findByPassengerCapacity() {
        //given
        InteriorInfo interiorInfo1 = new InteriorInfo(
                null,
                2,
                GearType.AUTOMATIC,
                0,
                10,
                "dirty white"
        );

        InteriorInfo interiorInfo2 = new InteriorInfo(
                null,
                3,
                GearType.MANUAL,
                6,
                1500,
                "grey"
        );

        interiorInfoRepositoryTest.save(interiorInfo1);
        interiorInfoRepositoryTest.save(interiorInfo2);

        Integer capacity = 3;

        //when
        List<InteriorInfo> interiorInfosFound =
                interiorInfoRepositoryTest.findByPassengerCapacity(capacity);

        assertThat(interiorInfo1).isNotIn(interiorInfosFound);
        assertThat(interiorInfo1).isIn(interiorInfosFound);
    }

    @Test
    void findByGearType() {
        //given
        InteriorInfo interiorInfo1 = new InteriorInfo(
                null,
                2,
                GearType.AUTOMATIC,
                0,
                10,
                "dirty white"
        );

        InteriorInfo interiorInfo2 = new InteriorInfo(
                null,
                3,
                GearType.MANUAL,
                6,
                1500,
                "grey"
        );

        interiorInfoRepositoryTest.save(interiorInfo1);
        interiorInfoRepositoryTest.save(interiorInfo2);

        String gearType = GearType.AUTOMATIC;

        //when
        List<InteriorInfo> interiorInfosFound = interiorInfoRepositoryTest.findByGearType(gearType);

        assertThat(interiorInfo1).isIn(interiorInfosFound);
        assertThat(interiorInfo1).isNotIn(interiorInfosFound);
    }

    @Test
    void findByTrunkCapacityIsGreaterThan() {
        //given
        InteriorInfo interiorInfo1 = new InteriorInfo(
                null,
                2,
                GearType.AUTOMATIC,
                0,
                10,
                "dirty white"
        );

        InteriorInfo interiorInfo2 = new InteriorInfo(
                null,
                3,
                GearType.MANUAL,
                6,
                1500,
                "grey"
        );

        interiorInfoRepositoryTest.save(interiorInfo1);
        interiorInfoRepositoryTest.save(interiorInfo2);

        Integer trunkCapacity = 1000;

        //when
        List<InteriorInfo> interiorInfosFound =
                interiorInfoRepositoryTest.findByTrunkCapacityIsGreaterThan(trunkCapacity);

        assertThat(interiorInfo1).isNotIn(interiorInfosFound);
        assertThat(interiorInfo1).isIn(interiorInfosFound);
    }

    @Test
    void findByInteriorColor() {
        //given
        InteriorInfo interiorInfo1 = new InteriorInfo(
                null,
                2,
                GearType.AUTOMATIC,
                0,
                10,
                "dirty white"
        );

        InteriorInfo interiorInfo2 = new InteriorInfo(
                null,
                3,
                GearType.MANUAL,
                6,
                1500,
                "grey"
        );

        interiorInfoRepositoryTest.save(interiorInfo1);
        interiorInfoRepositoryTest.save(interiorInfo2);

        String color = "grey";

        //when
        List<InteriorInfo> interiorInfosFound =
                interiorInfoRepositoryTest.findByInteriorColor(color);

        assertThat(interiorInfo1).isNotIn(interiorInfosFound);
        assertThat(interiorInfo1).isIn(interiorInfosFound);
    }
}