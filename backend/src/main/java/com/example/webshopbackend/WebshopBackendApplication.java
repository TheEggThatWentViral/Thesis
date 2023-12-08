package com.example.webshopbackend;

import com.example.webshopbackend.config.security.NotionConfigurationProperties;
import com.example.webshopbackend.domain.*;
import com.example.webshopbackend.service.JobService;
import com.example.webshopbackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@EnableConfigurationProperties(NotionConfigurationProperties.class)
public class WebshopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
        UserService userService,
        JobService jobService
    ) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            User user = new User(
                    null,
                    "Marcell",
                    "Nagy",
                    "mike",
                    "horvathmarcit@gmail.com",
                    "Password12!",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    5,
                    5
            );

            User user2 = new User(
                    null,
                    "Marcell",
                    "Nagy",
                    "kieran",
                    "horvathmarcit@gmail.com",
                    "Password12!",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    5,
                    5
            );

            userService.saveUser(user);
            userService.saveUser(user2);

            userService.addRoleToUser("marci", "ROLE_USER");
            userService.addRoleToUser("kieran", "ROLE_USER");

            jobService.saveJob(
                    new AdvertisedJob(
                            null,
                            "Cleaning",
                            "Cleaning a house",
                            new Address(
                                    null,
                                    "Hungary",
                                    "Miskolc",
                                    "1234",
                                    "Petőfi utca",
                                    "12."
                            ),
                            "35$",
                            JobState.ACTIVE,
                            "img",
                            "8 hours",
                            user
                    )
            );

            jobService.saveJob(
                    new AdvertisedJob(
                            null,
                            "Driving",
                            "Driving some trucks",
                            new Address(
                                    null,
                                    "England",
                                    "London",
                                    "1234",
                                    "Mark Twain street",
                                    "23."
                            ),
                            "50$",
                            JobState.ACTIVE,
                            "img",
                            "2 days",
                            user
                    )
            );

            /*userService.saveUser(
                    new User(
                            null,
                            "Marcell",
                            "Nagy",
                            "nmarcell05",
                            "nmarcell05@gmail.com",
                            "Password123!",
                            new ArrayList<>(),
                            new Checkout()
                    )
            );
            userService.saveUser(
                    new User(
                            null,
                            "Ákos",
                            "Kovács",
                            "kakoska",
                            "kakoska@gmail.com",
                            "Password123!",
                            new ArrayList<>(),
                            new Checkout()
                    )
            );
            userService.saveUser(
                    new User(
                            null,
                            "Béres",
                            "Doktor",
                            "DrBRS",
                            "DrBrs@gmail.com",
                            "Password123!",
                            new ArrayList<>(),
                            new Checkout()
                    )
            );

            userService.addRoleToUser("nmarcell05", "ROLE_USER");
            userService.addRoleToUser("nmarcell05", "ROLE_ADMIN");
            userService.addRoleToUser("kakoska", "ROLE_USER");
            userService.addRoleToUser("DrBRS", "ROLE_USER");*/
        };
    }
}
