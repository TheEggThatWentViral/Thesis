package com.example.webshopbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String vehicleType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "basicinfo_id", referencedColumnName = "id")
    private BasicInfo basicInfo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interiorinfo_id", referencedColumnName = "id")
    private InteriorInfo interiorInfo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exteriorinfo_id", referencedColumnName = "id")
    private ExteriorInfo exteriorInfo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engineinfo_id", referencedColumnName = "id")
    private EngineInfo engineInfo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Collection<Review> reviews = new ArrayList<>();
}