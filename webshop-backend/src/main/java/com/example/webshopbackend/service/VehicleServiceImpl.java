package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.*;
import com.example.webshopbackend.domain.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BasicInfoRepository basicInfoRepository;
    private final InteriorInfoRepository interiorInfoRepository;
    private final ExteriorInfoRepository exteriorInfoRepository;
    private final EngineInfoRepository engineInfoRepository;

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        log.info("Saving new vehicle with ID {} into database", vehicle.getId());
        basicInfoRepository.save(vehicle.getBasicInfo());
        interiorInfoRepository.save(vehicle.getInteriorInfo());
        exteriorInfoRepository.save(vehicle.getExteriorInfo());
        engineInfoRepository.save(vehicle.getEngineInfo());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findVehicleById(id);

        log.info("Deleting vehicle with ID {}", id);
        basicInfoRepository.delete(vehicle.getBasicInfo());
        interiorInfoRepository.delete(vehicle.getInteriorInfo());
        exteriorInfoRepository.delete(vehicle.getExteriorInfo());
        engineInfoRepository.delete(vehicle.getEngineInfo());
        vehicleRepository.delete(vehicle);
    }

    @Override
    public Vehicle getVehicle(Long id) {
        log.info("Fetching vehicle with ID {}", id);
        return vehicleRepository.findVehicleById(id);
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        log.info("Fetching all vehicles");
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getVehicleByType(String vehicleType) {
        log.info("Fetching vehicle with type {}", vehicleType);
        return vehicleRepository.findVehicleByVehicleType(vehicleType);
    }

    // START REGION BASIC INFO

    @Override
    public List<Vehicle> getVehicleByName(String name) {
        log.info("Fetching vehicle with name {}", name);
        List<BasicInfo> basicInfoList = basicInfoRepository.findByNameContains(name);
        List<Vehicle> vehicles = new ArrayList<>();

        for (BasicInfo basicInfo : basicInfoList) {
            vehicles.add(vehicleRepository.findVehicleByBasicInfo(basicInfo));
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByPrice(Integer min, Integer max) {
        log.info("Fetching vehicle with price between {} and {}", min, max);
        List<BasicInfo> basicInfoList = basicInfoRepository.findByPriceBetween(min, max);
        List<Vehicle> vehicles = new ArrayList<>();

        for (BasicInfo basicInfo : basicInfoList) {
            vehicles.add(vehicleRepository.findVehicleByBasicInfo(basicInfo));
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByProducer(String producer) {
        log.info("Fetching vehicle with producer {}", producer);
        List<BasicInfo> basicInfoList = basicInfoRepository.findByProducerContains(producer);
        List<Vehicle> vehicles = new ArrayList<>();

        for (BasicInfo basicInfo : basicInfoList) {
            vehicles.add(vehicleRepository.findVehicleByBasicInfo(basicInfo));
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByYearOfProduction(Integer min, Integer max) {
        log.info("Fetching vehicle with production year between {} and {}", min, max);
        List<BasicInfo> basicInfoList = basicInfoRepository.findByYearOfProductionBetween(min, max);
        List<Vehicle> vehicles = new ArrayList<>();

        for (BasicInfo basicInfo : basicInfoList) {
            vehicles.add(vehicleRepository.findVehicleByBasicInfo(basicInfo));
        }

        return vehicles;
    }

    // END REGION

    // START REGION INTERIOR INFO

    @Override
    public List<Vehicle> getVehicleByPassengerCapacity(Integer capacity) {
        log.info("Fetching vehicle with passenger capacity {}", capacity);
        List<InteriorInfo> interiorInfoList = interiorInfoRepository.findByPassengerCapacity(capacity);
        List<Vehicle> vehicles = new ArrayList<>();

        for (InteriorInfo interiorInfo : interiorInfoList) {
            vehicles.add(vehicleRepository.findVehicleByInteriorInfo(interiorInfo));
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByGearType(String gearType) {
        log.info("Fetching vehicle with gear type {}", gearType);
        List<InteriorInfo> interiorInfoList = interiorInfoRepository.findByGearType(gearType);
        List<Vehicle> vehicles = new ArrayList<>();

        for (InteriorInfo interiorInfo : interiorInfoList) {
            vehicles.add(vehicleRepository.findVehicleByInteriorInfo(interiorInfo));
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByTrunkCapacity(Integer capacity) {
        log.info("Fetching vehicle with trunk capacity {}", capacity);
        List<InteriorInfo> interiorInfoList = interiorInfoRepository.findByTrunkCapacityIsGreaterThan(capacity);
        List<Vehicle> vehicles = new ArrayList<>();

        for (InteriorInfo interiorInfo : interiorInfoList) {
            vehicles.add(vehicleRepository.findVehicleByInteriorInfo(interiorInfo));
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByInteriorColor(String color) {
        log.info("Fetching vehicle with interior color {}", color);
        List<InteriorInfo> interiorInfoList = interiorInfoRepository.findByInteriorColor(color);
        List<Vehicle> vehicles = new ArrayList<>();

        for (InteriorInfo interiorInfo : interiorInfoList) {
            vehicles.add(vehicleRepository.findVehicleByInteriorInfo(interiorInfo));
        }

        return vehicles;
    }

    // END REGION

    // START REGION EXTERIOR INFO

    @Override
    public List<Vehicle> getVehicleByExteriorColor(String color) {
        log.info("Fetching vehicle with exterior color {}", color);
        List<ExteriorInfo> exteriorInfoList = exteriorInfoRepository.findByExteriorColor(color);
        List<Vehicle> vehicles = new ArrayList<>();

        for (ExteriorInfo exteriorInfo : exteriorInfoList) {
            vehicles.add(vehicleRepository.findVehicleByExteriorInfo(exteriorInfo));
        }

        return vehicles;
    }

    // END REGION

    //START REGION ENGINE INFO

    @Override
    public List<Vehicle> getVehicleByEngineType(String engineType) {
        log.info("Fetching vehicle with engine type {}", engineType);
        List<EngineInfo> engineInfoList = engineInfoRepository.findByEngineType(engineType);
        List<Vehicle> vehicles = new ArrayList<>();

        for (EngineInfo engineInfo : engineInfoList) {
            vehicles.add(vehicleRepository.findVehicleByEngineInfo(engineInfo));
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByFuelType(String fuelType) {
        log.info("Fetching vehicle with fuel type {}", fuelType);
        List<EngineInfo> engineInfoList = engineInfoRepository.findByFuelType(fuelType);
        List<Vehicle> vehicles = new ArrayList<>();

        for (EngineInfo engineInfo : engineInfoList) {
            vehicles.add(vehicleRepository.findVehicleByEngineInfo(engineInfo));
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByWheelDrive(WheelDrive wheelDrive) {
        log.info("Fetching vehicle with wheel drive {}", wheelDrive);
        List<EngineInfo> engineInfoList = engineInfoRepository.findByWheelDrive(wheelDrive);
        List<Vehicle> vehicles = new ArrayList<>();

        for (EngineInfo engineInfo : engineInfoList) {
            vehicles.add(vehicleRepository.findVehicleByEngineInfo(engineInfo));
        }

        return vehicles;
    }

    // END REGION
}
