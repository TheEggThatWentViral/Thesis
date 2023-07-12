package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressById(Long id);
}
