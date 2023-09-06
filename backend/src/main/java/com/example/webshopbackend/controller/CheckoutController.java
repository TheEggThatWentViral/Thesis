package com.example.webshopbackend.controller;

import com.example.webshopbackend.domain.*;
import com.example.webshopbackend.service.CheckoutService;
import com.example.webshopbackend.service.OrderService;
import com.example.webshopbackend.service.UserService;
import com.example.webshopbackend.service.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
@Slf4j
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final UserService userService;
    private final VehicleService vehicleService;
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCheckoutById(@PathVariable("id") Long id) {
        Checkout checkout = checkoutService.getCheckout(id);
        if (checkout == null) {
            log.error("The database doesn't contain a checkout record with ID {}", id);
            return  ResponseEntity.notFound().build();
        }
        log.info("Checkout with ID {} was found in the database", id);
        return ResponseEntity.ok().body(checkout);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCheckout(@RequestBody Checkout checkout) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/api/checkout/save").toUriString()
        );

        return ResponseEntity.created(uri).body(checkoutService.saveCheckout(checkout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCheckout(@PathVariable("id") Long id) {
        if (checkoutService.getCheckout(id) == null) {
            log.error("The database doesn't contain a checkout record with ID {}", id);
            return ResponseEntity.notFound().build();
        }

        checkoutService.deleteCheckout(id);
        return ResponseEntity.ok().build();
    }

    /*@PostMapping("/initialize")
    public ResponseEntity<?> initializeCheckout(@RequestBody CheckoutForm checkoutForm) {
        User user = userService.getUser(checkoutForm.getUsername());
        if (userService.getUser(user.getUsername()) == null) {
            log.error("The database doesn't contain user {}", user.getUsername());
            return ResponseEntity.notFound().build();
        }

        Vehicle vehicle = vehicleService.getVehicle(checkoutForm.getVehicleId());
        if (vehicleService.getVehicle(vehicle.getId()) == null) {
            log.error("The database doesn't contain vehicle with ID {}", vehicle.getId());
            return ResponseEntity.notFound().build();
        }

        log.info("Initializing new checkout");
        Checkout checkout = checkoutService.saveCheckout(
                new Checkout(
                        null,
                        vehicle,
                        new Address(),
                        user.getFirstName(),
                        user.getLastName()
                )
        );

        user.setCheckout(checkout);
        userService.saveUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseVehicle(@RequestBody String username) {
        User user = userService.getUser(username);
        Checkout checkout = checkoutService.getCheckout(user.getCheckout().getId());

        Order newOrder = new Order(
                null,
                checkout.getVehicle().getId(),
                checkout.getVehicle().getBasicInfo().getName(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail()
        );
        Order savedOrder = orderService.saveOrder(newOrder);

        user.setCheckout(null);

        try {
            orderService.sendOrder(savedOrder);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }*/
}

@Data
class CheckoutForm {
    private String username;
    private Long vehicleId;
}
