package com.example.webshopbackend.controller;

import com.example.webshopbackend.domain.*;
import com.example.webshopbackend.service.ReviewService;
import com.example.webshopbackend.service.VehicleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable("id") Long id) {
        Vehicle vehicle = vehicleService.getVehicle(id);
        if (vehicle == null) {
            log.error("The database doesn't contain a vehicle record with ID {}", id);
            return  ResponseEntity.notFound().build();
        }
        log.info("Vehicle with ID {} was found in the database", id);
        return ResponseEntity.ok().body(vehicle);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle) {
        if (vehicleService.getVehicle(vehicle.getId()) != null) {
            log.error("Vehicle with ID {} is already exist in the database", vehicle.getId());
            return ResponseEntity.badRequest().build();
        }

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/api/vehicles/save").toUriString()
        );

        return ResponseEntity.created(uri).body(vehicleService.saveVehicle(vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long id) {
        if (vehicleService.getVehicle(id) == null) {
            log.error("The database doesn't contain a vehicle record with ID {}", id);
            return ResponseEntity.notFound().build();
        }

        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok().body(vehicleService.getAllVehicle());
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Vehicle>> getAllCars() {
        return ResponseEntity.ok().body(vehicleService.getVehicleByType(VehicleType.CAR));
    }

    @GetMapping("/motorcycles")
    public ResponseEntity<List<Vehicle>> getAllMotorcycles() {
        return ResponseEntity.ok().body(vehicleService.getVehicleByType(VehicleType.MOTORCYCLE));
    }

    @GetMapping("/vans")
    public ResponseEntity<List<Vehicle>> getAllVans() {
        return ResponseEntity.ok().body(vehicleService.getVehicleByType(VehicleType.VAN));
    }

    @PostMapping("/{id}/add_review")
    public ResponseEntity<?> addReview(
            @PathVariable Long id,
            @RequestBody Review review
    ) {
        reviewService.saveReview(id, review);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete_review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    // START REGION BASIC INFO

    @GetMapping("/filter/name")
    public ResponseEntity<List<Vehicle>> getVehicleByName(@RequestBody String name) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByName(name));
    }

    @GetMapping("/filter/price")
    public ResponseEntity<List<Vehicle>> getVehicleByPrice(@RequestBody MinMaxForm values) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByPrice(values.getMin(), values.getMax()));
    }

    @GetMapping("/filter/producer")
    public ResponseEntity<List<Vehicle>> getVehicleByProducer(@RequestBody String producer) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByProducer(producer));
    }

    @GetMapping("/filter/production_year")
    public ResponseEntity<List<Vehicle>> getVehicleByYearOfProduction(@RequestBody MinMaxForm values) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByYearOfProduction(values.getMin(), values.getMax()));
    }

    // END REGION

    // START REGION INTERIOR INFO

    @GetMapping("/filter/passenger_capacity")
    public ResponseEntity<List<Vehicle>> getVehicleByPassengerCapacity(@RequestBody Integer capacity) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByPassengerCapacity(capacity));
    }

    @GetMapping("/filter/gear_type")
    public ResponseEntity<List<Vehicle>> getVehicleByGearType(@RequestBody String gearType) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByGearType(gearType));
    }

    @GetMapping("/filter/trunk_capacity")
    public ResponseEntity<List<Vehicle>> getVehicleByTrunkCapacity(@RequestBody Integer capacity) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByTrunkCapacity(capacity));
    }

    @GetMapping("/filter/interior_color")
    public ResponseEntity<List<Vehicle>> getVehicleByInteriorColor(@RequestBody String color) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByInteriorColor(color));
    }

    // END REGION

    // START REGION EXTERIOR INFO

    @GetMapping("/filter/exterior_color")
    public ResponseEntity<List<Vehicle>> getVehicleByExteriorColor(@RequestBody String color) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByExteriorColor(color));
    }

    // END REGION

    // START REGION ENGINE INFO

    @GetMapping("/filter/engine_type")
    public ResponseEntity<List<Vehicle>> getVehicleByEngineType(@RequestBody String engineType) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByEngineType(engineType));
    }

    @GetMapping("/filter/fuel_type")
    public ResponseEntity<List<Vehicle>> getVehicleByFuelType(@RequestBody String fuelType) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByFuelType(fuelType));
    }

    @GetMapping("/filter/wheel_drive")
    public ResponseEntity<List<Vehicle>> getVehicleByWheelDrive(@RequestBody WheelDrive wheelDrive) {
        return ResponseEntity.ok().body(vehicleService.getVehicleByWheelDrive(wheelDrive));
    }
}

@Data
class MinMaxForm {
    private Integer min;
    private Integer max;
}
