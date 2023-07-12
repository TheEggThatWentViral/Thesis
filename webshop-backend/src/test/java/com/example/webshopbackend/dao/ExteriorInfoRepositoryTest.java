package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.ExteriorInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExteriorInfoRepositoryTest {

    @Autowired
    ExteriorInfoRepository exteriorInfoRepositoryTest;

    @Test
    void findByExteriorColor() {
        //given
        ExteriorInfo exteriorInfo1 = new ExteriorInfo(
                null,
                3150,
                7.0,
                4.2,
                2,
                "blue"
        );

        ExteriorInfo exteriorInfo2 = new ExteriorInfo(
                null,
                100,
                3.0,
                0.8,
                0,
                "cyan"
        );

        exteriorInfoRepositoryTest.save(exteriorInfo1);
        exteriorInfoRepositoryTest.save(exteriorInfo2);

        String color = "cyan";

        //when
        List<ExteriorInfo> exteriorInfosFound = exteriorInfoRepositoryTest.findByExteriorColor(color);

        //then
        assertThat(exteriorInfo1).isNotIn(exteriorInfosFound);
        assertThat(exteriorInfo2).isIn(exteriorInfosFound);
    }
}