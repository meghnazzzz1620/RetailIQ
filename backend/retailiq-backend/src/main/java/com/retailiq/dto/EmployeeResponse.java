package com.retailiq.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String designation;

    private String department;

    private Double salary;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}