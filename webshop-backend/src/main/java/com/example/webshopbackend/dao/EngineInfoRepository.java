package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.EngineInfo;
import com.example.webshopbackend.domain.EngineType;
import com.example.webshopbackend.domain.FuelType;
import com.example.webshopbackend.domain.WheelDrive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngineInfoRepository extends JpaRepository<EngineInfo, Long> {

    List<EngineInfo> findByEngineType(String type);

    List<EngineInfo> findByFuelType(String type);

    List<EngineInfo> findByWheelDrive(WheelDrive wheelDrive);
}
