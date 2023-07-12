package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.BasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasicInfoRepository extends JpaRepository<BasicInfo, Long> {

    List<BasicInfo> findByNameContains(String name);

    List<BasicInfo> findByPriceBetween(Integer min, Integer max);

    //List<BasicInfo> findByPriceIsLessThan(Integer max);

    //List<BasicInfo> findByPriceIsGreaterThan(Integer min);

    List<BasicInfo> findByProducerContains(String producer);

    List<BasicInfo> findByYearOfProductionBetween(Integer min, Integer max);
}
