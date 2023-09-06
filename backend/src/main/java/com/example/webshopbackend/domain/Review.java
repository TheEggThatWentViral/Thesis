package com.example.webshopbackend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Min(1)
    @Max(10)
    private Integer rating;

    @Size(
        min = 15,
        max = 100,
        message = "The length of the review must be between 15 and 100 characters."
    )
    private String reviewComment;

    @NotNull(message = "The author can't be null.")
    private String author;

    @DateTimeFormat
    private Date dateOfPosting = new Date();
}
