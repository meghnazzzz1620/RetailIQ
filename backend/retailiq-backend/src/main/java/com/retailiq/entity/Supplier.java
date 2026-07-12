package com.retailiq.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(nullable = false, unique = true)
    private String supplierCode;

    @Column(nullable = false)
    private String companyName;

    @Column(unique = true)
    private String gstNumber;

    private String contactPerson;

    @Column(unique = true)
    private String email;

    private String phone;

    private String street;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private Double supplierRating;

    @Column(nullable = false)
    private Boolean status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}