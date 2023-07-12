package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.AddressRepository;
import com.example.webshopbackend.dao.CheckoutRepository;
import com.example.webshopbackend.dao.UserRepository;
import com.example.webshopbackend.domain.Address;
import com.example.webshopbackend.domain.Checkout;
import com.example.webshopbackend.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final AddressRepository addressRepository;

    @Override
    public Checkout saveCheckout(Checkout checkout) {
        log.info("Saving checkout with ID {}", checkout.getId());
        addressRepository.save(checkout.getBillingAddress());
        return checkoutRepository.save(checkout);
    }

    @Override
    public Checkout getCheckout(Long id) {
        log.info("Fetching checkout with ID {}", id);
        return checkoutRepository.findCheckoutById(id);
    }

    @Override
    public void deleteCheckout(Long id) {
        log.info("Deleting checkout with ID {}", id);
        Checkout checkout = checkoutRepository.findCheckoutById(id);
        Address address = addressRepository.findAddressById(checkout.getBillingAddress().getId());
        addressRepository.delete(address);
        checkoutRepository.delete(checkout);
    }
}
