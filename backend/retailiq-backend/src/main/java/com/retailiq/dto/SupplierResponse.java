package com.retailiq.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse {

    private Long supplierId;
    private String supplierCode;
    private String companyName;
    private String gstNumber;
    private String contactPerson;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private Double supplierRating;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}