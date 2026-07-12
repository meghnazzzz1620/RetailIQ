package com.retailiq.mapper;

import com.retailiq.dto.SupplierRequest;
import com.retailiq.dto.SupplierResponse;
import com.retailiq.entity.Supplier;

import java.time.LocalDateTime;

public class SupplierMapper {

    public static Supplier toEntity(SupplierRequest request) {

        return Supplier.builder()
                .supplierCode(request.getSupplierCode())
                .companyName(request.getCompanyName())
                .gstNumber(request.getGstNumber())
                .contactPerson(request.getContactPerson())
                .email(request.getEmail())
                .phone(request.getPhone())
                .street(request.getStreet())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())

                // Default values
                .supplierRating(5.0)
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())

                .build();
    }

    public static SupplierResponse toResponse(Supplier supplier) {

        return SupplierResponse.builder()
                .supplierId(supplier.getSupplierId())
                .supplierCode(supplier.getSupplierCode())
                .companyName(supplier.getCompanyName())
                .gstNumber(supplier.getGstNumber())
                .contactPerson(supplier.getContactPerson())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .street(supplier.getStreet())
                .city(supplier.getCity())
                .state(supplier.getState())
                .country(supplier.getCountry())
                .postalCode(supplier.getPostalCode())
                .supplierRating(supplier.getSupplierRating())
                .status(supplier.getStatus())
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .build();
    }
}