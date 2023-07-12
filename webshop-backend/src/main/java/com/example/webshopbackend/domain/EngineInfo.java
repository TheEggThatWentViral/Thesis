package com.example.webshopbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "engineinfo")
public class EngineInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String engineType;
    private Integer enginePower;
    private String fuelType;
    private Double fuelCapacity;
    private WheelDrive wheelDrive;
}
