package com.example.webshopbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interiorinfo")
public class InteriorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Integer passengerCapacity;
    private String gearType;
    private Integer numberOfGears;
    private Integer trunkCapacity;
    private String interiorColor;
}
