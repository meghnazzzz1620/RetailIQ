package com.retailiq.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseRequest {

    @NotBlank(message = "Warehouse Code is required")
    private String warehouseCode;

    @NotBlank(message = "Warehouse Name is required")
    private String warehouseName;

    private String managerName;

    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

}