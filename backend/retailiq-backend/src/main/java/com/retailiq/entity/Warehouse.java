package com.retailiq.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @Column(nullable = false, unique = true)
    private String warehouseCode;

    @Column(nullable = false)
    private String warehouseName;

    private String managerName;

    private String phone;

    private String email;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    @Column(nullable = false)
    private Boolean status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}