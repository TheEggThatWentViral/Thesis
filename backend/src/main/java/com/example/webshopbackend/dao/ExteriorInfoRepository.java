package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.ExteriorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExteriorInfoRepository extends JpaRepository<ExteriorInfo, Long> {
    List<ExteriorInfo> findByExteriorColor(String color);
}
