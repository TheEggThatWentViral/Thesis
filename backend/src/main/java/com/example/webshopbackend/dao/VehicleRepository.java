package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findVehicleById(Long id);

    List<Vehicle> findVehicleByVehicleType(String vehicleType);

    Vehicle findVehicleByBasicInfo(BasicInfo basicInfo);

    Vehicle findVehicleByInteriorInfo(InteriorInfo interiorInfo);

    Vehicle findVehicleByExteriorInfo(ExteriorInfo exteriorInfo);

    Vehicle findVehicleByEngineInfo(EngineInfo engineInfo);
}
