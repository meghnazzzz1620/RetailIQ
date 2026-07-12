package com.retailiq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierReportResponse {

    private String contactPerson;

    private String companyName;

    private String email;

    private String phone;

    private String city;

    private String state;

    private String status;

}