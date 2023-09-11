package com.example.webshopbackend;

import com.example.webshopbackend.config.security.NotionConfigurationProperties;
import com.example.webshopbackend.domain.*;
import com.example.webshopbackend.service.OrderService;
import com.example.webshopbackend.service.UserService;
import com.example.webshopbackend.service.VehicleService;
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
        VehicleService vehicleService,
        OrderService orderService
    ) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(
                    new User(
                            null,
                            "Marcell",
                            "Nagy",
                            "marci",
                            "horvathmarcit@gmail.com",
                            "Password12!",
                            new ArrayList<>(),
                            new ArrayList<>(),
                            5,
                            5
                    )
            );

            userService.addRoleToUser("marci", "ROLE_USER");

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

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.CAR,
                            new BasicInfo(
                                    null,
                                    "Toyota Corolla",
                                    25000,
                                    "Toyota",
                                    2014
                            ),
                            new InteriorInfo(
                                    null,
                                    5,
                                    GearType.MANUAL,
                                    5,
                                    250,
                                    "kaki"
                            ),
                            new ExteriorInfo(
                                    null,
                                    2000,
                                    6.2,
                                    3.5,
                                    4,
                                    "red"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.TRADITIONAL,
                                    110,
                                    FuelType.DIESEL,
                                    10.0,
                                    WheelDrive.TWO_WHEEL_FRONT
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                5,
                                                "It's not a luxury car but it is a good deal for the price.",
                                                "JoskaBacsi64",
                                                new GregorianCalendar(2018, Calendar.MARCH, 20).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                6,
                                                "It's alright, feels good to drive.",
                                                "VinDiesel999",
                                                new GregorianCalendar(2020, Calendar.NOVEMBER, 3).getTime()
                                        )
                                );
                            }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.CAR,
                            new BasicInfo(
                                    null,
                                    "Ford Transit",
                                    22000,
                                    "Ford",
                                    2016
                            ),
                            new InteriorInfo(
                                    null,
                                    3,
                                    GearType.MANUAL,
                                    6,
                                    1500,
                                    "grey"
                            ),
                            new ExteriorInfo(
                                    null,
                                    3150,
                                    7.0,
                                    4.2,
                                    2,
                                    "blue"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.TRADITIONAL,
                                    200,
                                    FuelType.PETROL,
                                    20.0,
                                    WheelDrive.TWO_WHEEL_FRONT
                            ),

                    new ArrayList<>() {{
                        add(
                                new Review(
                                        null,
                                        7,
                                        "Great for transporting things!",
                                        "MekkElek01",
                                        new GregorianCalendar(2017, Calendar.JUNE, 11).getTime()
                                )
                        );
                        add(
                                new Review(
                                        null,
                                        8,
                                        "It helped me a lot when I was moving.",
                                        "HiBob7",
                                        new GregorianCalendar(2020, Calendar.DECEMBER, 24).getTime()
                                )
                        );
                    }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.CAR,
                            new BasicInfo(
                                    null,
                                    "Tesla Model S",
                                    130000,
                                    "Tesla",
                                    2023
                            ),
                            new InteriorInfo(
                                    null,
                                    5,
                                    GearType.AUTOMATIC,
                                    6,
                                    3000,
                                    "white"
                            ),
                            new ExteriorInfo(
                                    null,
                                    1950,
                                    7.0,
                                    4.2,
                                    5,
                                    "red"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.ELECTRIC,
                                    1000,
                                    FuelType.ELECTRIC_POWER,
                                    0.0,
                                    WheelDrive.FOUR_WHEEL
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                8,
                                                "Tesla gonna take over, there is no question about it.",
                                                "TeslaToTheMars",
                                                new GregorianCalendar(2017, Calendar.JUNE, 11).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                1,
                                                "Why would you spend this much money just to prove to others that you are \"greener\" than them?",
                                                "DeadDinosaurs4Ever",
                                                new GregorianCalendar(2020, Calendar.DECEMBER, 24).getTime()
                                        )
                                );
                            }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.VAN,
                            new BasicInfo(
                                    null,
                                    "Mercedes V-Class",
                                    80000,
                                    "Mercedes",
                                    2018
                            ),
                            new InteriorInfo(
                                    null,
                                    6,
                                    GearType.AUTOMATIC,
                                    6,
                                    3000,
                                    "grey"
                            ),
                            new ExteriorInfo(
                                    null,
                                    3150,
                                    7.0,
                                    4.2,
                                    4,
                                    "black"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.TRADITIONAL,
                                    250,
                                    FuelType.PETROL,
                                    60.0,
                                    WheelDrive.TWO_WHEEL_FRONT
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                10,
                                                "Best van ever! Absolutely love it!",
                                                "NotGreatNotTerrible624",
                                                new GregorianCalendar(2017, Calendar.JUNE, 11).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                8,
                                                "Really comfortable to travel with.",
                                                "OhHiMark73",
                                                new GregorianCalendar(2020, Calendar.DECEMBER, 24).getTime()
                                        )
                                );
                            }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.CAR,
                            new BasicInfo(
                                    null,
                                    "Ferrari F40",
                                    2000000,
                                    "Ferrari",
                                    1999
                            ),
                            new InteriorInfo(
                                    null,
                                    2,
                                    GearType.MANUAL,
                                    6,
                                    0,
                                    "black"
                            ),
                            new ExteriorInfo(
                                    null,
                                    1750,
                                    7.0,
                                    4.2,
                                    2,
                                    "red"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.TRADITIONAL,
                                    471,
                                    FuelType.PETROL,
                                    40.0,
                                    WheelDrive.TWO_WHEEL_BACK
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                10,
                                                "Legendary car!!",
                                                "DontDrinkAndDrive007",
                                                new GregorianCalendar(2018, Calendar.JUNE, 11).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                10,
                                                "Everyone's dream car",
                                                "NoShitSherlock420",
                                                new GregorianCalendar(2021, Calendar.DECEMBER, 24).getTime()
                                        )
                                );
                            }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.CAR,
                            new BasicInfo(
                                    null,
                                    "McLaren 720s",
                                    350000,
                                    "McLaren",
                                    2017
                            ),
                            new InteriorInfo(
                                    null,
                                    2,
                                    GearType.MANUAL,
                                    6,
                                    500,
                                    "grey"
                            ),
                            new ExteriorInfo(
                                    null,
                                    2050,
                                    7.0,
                                    4.2,
                                    2,
                                    "white"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.TRADITIONAL,
                                    720,
                                    FuelType.PETROL,
                                    45.0,
                                    WheelDrive.TWO_WHEEL_FRONT
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                9,
                                                "Absolut BEAST of a vehicle!",
                                                "JustaCarguy6",
                                                new GregorianCalendar(2015, Calendar.JUNE, 11).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                8,
                                                "Only experienced drivers should be allowed to drive this rocket",
                                                "Namaste99",
                                                new GregorianCalendar(2022, Calendar.FEBRUARY, 21).getTime()
                                        )
                                );
                            }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.CAR,
                            new BasicInfo(
                                    null,
                                    "BMW X3",
                                    10000000,
                                    "BMW",
                                    2021
                            ),
                            new InteriorInfo(
                                    null,
                                    5,
                                    GearType.AUTOMATIC,
                                    8,
                                    3500,
                                    "black"
                            ),
                            new ExteriorInfo(
                                    null,
                                    2400,
                                    7.0,
                                    4.2,
                                    2,
                                    "darkBlue"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.HYBRID,
                                    297,
                                    FuelType.DIESEL,
                                    20.0,
                                    WheelDrive.FOUR_WHEEL
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                8,
                                                "Great car for a family!!",
                                                "SomeGuy84",
                                                new GregorianCalendar(2022, Calendar.MARCH, 27).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                8,
                                                "A very safe choice, if you want a reliable car.",
                                                "CarLover36",
                                                new GregorianCalendar(2022, Calendar.DECEMBER, 1).getTime()
                                        )
                                );
                            }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.MOTORCYCLE,
                            new BasicInfo(
                                    null,
                                    "Yamaha MT-09",
                                    40000,
                                    "Yamaha",
                                    2014
                            ),
                            new InteriorInfo(
                                    null,
                                    1,
                                    GearType.AUTOMATIC,
                                    6,
                                    0,
                                    "black"
                            ),
                            new ExteriorInfo(
                                    null,
                                    400,
                                    3.0,
                                    1.2,
                                    0,
                                    "black"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.TRADITIONAL,
                                    150,
                                    FuelType.DIESEL,
                                    25.0,
                                    WheelDrive.TWO_WHEEL_BACK
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                9,
                                                "Driving a bike isn't a life insurance, but the feeling worth it",
                                                "JumpHighLittleGuy",
                                                new GregorianCalendar(2017, Calendar.JUNE, 11).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                5,
                                                "Personally, I like the MT-07 more.",
                                                "GameOfBikes66",
                                                new GregorianCalendar(2020, Calendar.DECEMBER, 24).getTime()
                                        )
                                );
                            }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.MOTORCYCLE,
                            new BasicInfo(
                                    null,
                                    "Harley Davidson Fat Boy",
                                    50000,
                                    "Harley Davidson",
                                    2014
                            ),
                            new InteriorInfo(
                                    null,
                                    1,
                                    GearType.AUTOMATIC,
                                    6,
                                    0,
                                    "black"
                            ),
                            new ExteriorInfo(
                                    null,
                                    600,
                                    3.0,
                                    1.2,
                                    0,
                                    "black-orange"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.TRADITIONAL,
                                    173,
                                    FuelType.DIESEL,
                                    25.0,
                                    WheelDrive.TWO_WHEEL_BACK
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                9,
                                                "Such a classic demon",
                                                "TheChosenOne01",
                                                new GregorianCalendar(2017, Calendar.JUNE, 11).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                2,
                                                "It's just a waste of money ",
                                                "Bill42",
                                                new GregorianCalendar(2020, Calendar.DECEMBER, 24).getTime()
                                        )
                                );
                            }}
                    )
            );

            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.MOTORCYCLE,
                            new BasicInfo(
                                    null,
                                    "Vespa",
                                    15000,
                                    "Vespa",
                                    2018
                            ),
                            new InteriorInfo(
                                    null,
                                    2,
                                    GearType.AUTOMATIC,
                                    0,
                                    10,
                                    "dirty white"
                            ),
                            new ExteriorInfo(
                                    null,
                                    100,
                                    3.0,
                                    0.8,
                                    0,
                                    "cyan"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.ELECTRIC,
                                    60,
                                    FuelType.ELECTRIC_POWER,
                                    10.0,
                                    WheelDrive.TWO_WHEEL_BACK
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                4,
                                                "It's really weak, and too slow for me...",
                                                "AbdrakaDaniel",
                                                new GregorianCalendar(2021, Calendar.JANUARY, 17).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                9,
                                                "I really love it, so smooth!",
                                                "SamuraiJack11",
                                                new GregorianCalendar(2022, Calendar.AUGUST, 30).getTime()
                                        )
                                );
                            }}
                    )
            );
            vehicleService.saveVehicle(
                    new Vehicle(
                            null,
                            VehicleType.VAN,
                            new BasicInfo(
                                    null,
                                    "Sprinter",
                                    2500000,
                                    "Mercedes-Benz",
                                    2013
                            ),
                            new InteriorInfo(
                                    null,
                                    3,
                                    GearType.MANUAL,
                                    6,
                                    25,
                                    "dirty white"
                            ),
                            new ExteriorInfo(
                                    null,
                                    800,
                                    4.0,
                                    1.3,
                                    2,
                                    "white"
                            ),
                            new EngineInfo(
                                    null,
                                    EngineType.TRADITIONAL,
                                    90,
                                    FuelType.DIESEL,
                                    25.0,
                                    WheelDrive.TWO_WHEEL_BACK
                            ),
                            new ArrayList<>() {{
                                add(
                                        new Review(
                                                null,
                                                6,
                                                "Huge capacity, got space for everything",
                                                "HordozóHerkules",
                                                new GregorianCalendar(2019, Calendar.JANUARY, 30).getTime()
                                        )
                                );
                                add(
                                        new Review(
                                                null,
                                                3,
                                                "Very slow, but it is kinda expected from a van.",
                                                "FastAndFuriousFan",
                                                new GregorianCalendar(2022, Calendar.JUNE, 13).getTime()
                                        )
                                );
                            }}
                    )
            );
        };
    }
}
