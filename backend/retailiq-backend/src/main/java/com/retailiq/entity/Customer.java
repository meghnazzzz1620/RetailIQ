package com.retailiq.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String phone;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    @Column(nullable=false)
    private Boolean status;

    @Column(updatable=false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}