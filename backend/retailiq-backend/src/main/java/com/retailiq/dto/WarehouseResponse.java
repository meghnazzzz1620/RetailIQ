package com.retailiq.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseResponse {

    private Long warehouseId;

    private String warehouseCode;

    private String warehouseName;

    private String managerName;

    private String phone;

    private String email;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}