package com.example.webshopbackend.service;

import com.example.webshopbackend.domain.*;

import java.util.List;


public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle);
    Vehicle getVehicle(Long id);
    List<Vehicle> getAllVehicle();
    void deleteVehicle(Long id);
    List<Vehicle> getVehicleByType(String vehicleType);

    // BASIC INFO
    List<Vehicle> getVehicleByName(String name);
    List<Vehicle> getVehicleByPrice(Integer min, Integer max);
    List<Vehicle> getVehicleByProducer(String producer);
    List<Vehicle> getVehicleByYearOfProduction(Integer min, Integer max);

    // INTERIOR INFO
    List<Vehicle> getVehicleByPassengerCapacity(Integer capacity);
    List<Vehicle> getVehicleByGearType(String gearType);
    List<Vehicle> getVehicleByTrunkCapacity(Integer capacity);
    List<Vehicle> getVehicleByInteriorColor(String color);

    // EXTERIOR INFO
    List<Vehicle> getVehicleByExteriorColor(String color);

    // ENGINE INFO
    List<Vehicle> getVehicleByEngineType(String engineType);
    List<Vehicle> getVehicleByFuelType(String fuelType);
    List<Vehicle> getVehicleByWheelDrive(WheelDrive wheelDrive);
}
