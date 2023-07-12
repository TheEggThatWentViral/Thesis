package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.BasicInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BasicInfoRepositoryTest {

    @Autowired
    BasicInfoRepository basicInfoRepositoryTest;

    @Test
    void canFindByNameContains() {
        //given
        BasicInfo basicInfo1 = new BasicInfo(
                null,
                "Toyota Corolla",
                5000000,
                "Toyota",
                2014
        );

        BasicInfo basicInfo2 = new BasicInfo(
                null,
                "Ford Transit",
                10000000,
                "Ford",
                2016
        );

        basicInfoRepositoryTest.save(basicInfo1);
        basicInfoRepositoryTest.save(basicInfo2);

        String nameString = "Toyota";

        //when
        List<BasicInfo> basicInfosFound = basicInfoRepositoryTest.findByNameContains(nameString);

        //then
        assertThat(basicInfo1).isIn(basicInfosFound);
        assertThat(basicInfo2).isNotIn(basicInfosFound);
    }

    @Test
    void canFindByPriceBetween() {
        //given
        BasicInfo basicInfo1 = new BasicInfo(
                null,
                "Toyota Corolla",
                5000000,
                "Toyota",
                2014
        );

        BasicInfo basicInfo2 = new BasicInfo(
                null,
                "Ford Transit",
                10000000,
                "Ford",
                2016
        );

        BasicInfo basicInfo3 = new BasicInfo(
                null,
                "Vespa",
                1000000,
                "Vespa",
                2018
        );

        basicInfoRepositoryTest.save(basicInfo1);
        basicInfoRepositoryTest.save(basicInfo2);
        basicInfoRepositoryTest.save(basicInfo3);

        Integer min = 2000000;
        Integer max = 8000000;

        //when
        List<BasicInfo> basicInfosFound = basicInfoRepositoryTest.findByPriceBetween(min, max);

        //then
        assertThat(basicInfo1).isIn(basicInfosFound);
        assertThat(basicInfo2).isNotIn(basicInfosFound);
        assertThat(basicInfo3).isNotIn(basicInfosFound);
    }

    @Test
    void canFindByProducerContains() {
        //given
        BasicInfo basicInfo1 = new BasicInfo(
                null,
                "Toyota Corolla",
                5000000,
                "Toyota",
                2014
        );

        BasicInfo basicInfo2 = new BasicInfo(
                null,
                "Ford Transit",
                10000000,
                "Ford",
                2016
        );

        basicInfoRepositoryTest.save(basicInfo1);
        basicInfoRepositoryTest.save(basicInfo2);

        String producerString = "Toy";

        //when
        List<BasicInfo> basicInfosFound = basicInfoRepositoryTest.findByProducerContains(producerString);

        //then
        assertThat(basicInfo1).isIn(basicInfosFound);
        assertThat(basicInfo2).isNotIn(basicInfosFound);
    }

    @Test
    void findByYearOfProductionBetween() {
        //given
        BasicInfo basicInfo1 = new BasicInfo(
                null,
                "Toyota Corolla",
                5000000,
                "Toyota",
                2014
        );

        BasicInfo basicInfo2 = new BasicInfo(
                null,
                "Ford Transit",
                10000000,
                "Ford",
                2016
        );

        BasicInfo basicInfo3 = new BasicInfo(
                null,
                "Vespa",
                1000000,
                "Vespa",
                2018
        );

        basicInfoRepositoryTest.save(basicInfo1);
        basicInfoRepositoryTest.save(basicInfo2);
        basicInfoRepositoryTest.save(basicInfo3);

        Integer min = 2015;
        Integer max = 2017;

        //when
        List<BasicInfo> basicInfosFound = basicInfoRepositoryTest.findByYearOfProductionBetween(min, max);

        //then
        assertThat(basicInfo1).isNotIn(basicInfosFound);
        assertThat(basicInfo2).isIn(basicInfosFound);
        assertThat(basicInfo3).isNotIn(basicInfosFound);
    }
}