package com.retailiq.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequest {

    @NotBlank
    private String supplierCode;

    @NotBlank
    private String companyName;

    private String gstNumber;

    private String contactPerson;

    @Email
    private String email;

    private String phone;

    private String street;

    private String city;

    private String state;

    private String country;

    private String postalCode;

}