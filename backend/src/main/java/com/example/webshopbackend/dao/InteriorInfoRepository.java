package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.GearType;
import com.example.webshopbackend.domain.InteriorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteriorInfoRepository extends JpaRepository<InteriorInfo, Long> {

    List<InteriorInfo> findByPassengerCapacity(Integer capacity);

    List<InteriorInfo> findByGearType(String gearType);

    List<InteriorInfo> findByTrunkCapacityIsGreaterThan(Integer capacity);

    List<InteriorInfo> findByInteriorColor(String color);
}
