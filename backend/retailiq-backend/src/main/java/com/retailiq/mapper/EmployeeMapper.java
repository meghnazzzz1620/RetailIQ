package com.retailiq.mapper;

import com.retailiq.dto.EmployeeRequest;
import com.retailiq.dto.EmployeeResponse;
import com.retailiq.entity.Employee;

import java.time.LocalDateTime;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequest request) {

        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .designation(request.getDesignation())
                .department(request.getDepartment())
                .salary(request.getSalary())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static EmployeeResponse toResponse(Employee employee) {

        return EmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .designation(employee.getDesignation())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .status(employee.getStatus())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }
}