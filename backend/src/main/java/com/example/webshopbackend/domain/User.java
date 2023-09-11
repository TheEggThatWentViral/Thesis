package com.example.webshopbackend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @Size(
        min = 5,
        max = 15,
        message = "The length of the username must be between 5 and 15 characters."
    )
    private String username;

    @Email(message = "The given email is not valid.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}$",
            message = "the length of the password must be at least 8 characters containing at least 1 uppercase, 1 lowercase, 1 special character and 1 digit "
    )
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobs_id", referencedColumnName = "id")
    private Collection<AdvertisedJob> jobsDone = new ArrayList<>();

    @Min(1)
    @Max(10)
    private Integer workerRating;

    @Min(1)
    @Max(10)
    private Integer publisherRating;
}
