package com.retailiq.mapper;

import com.retailiq.dto.WarehouseRequest;
import com.retailiq.dto.WarehouseResponse;
import com.retailiq.entity.Warehouse;

import java.time.LocalDateTime;

public class WarehouseMapper {

    public static Warehouse toEntity(WarehouseRequest request) {

        return Warehouse.builder()
                .warehouseCode(request.getWarehouseCode())
                .warehouseName(request.getWarehouseName())
                .managerName(request.getManagerName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static WarehouseResponse toResponse(Warehouse warehouse) {

        return WarehouseResponse.builder()
                .warehouseId(warehouse.getWarehouseId())
                .warehouseCode(warehouse.getWarehouseCode())
                .warehouseName(warehouse.getWarehouseName())
                .managerName(warehouse.getManagerName())
                .phone(warehouse.getPhone())
                .email(warehouse.getEmail())
                .address(warehouse.getAddress())
                .city(warehouse.getCity())
                .state(warehouse.getState())
                .country(warehouse.getCountry())
                .postalCode(warehouse.getPostalCode())
                .status(warehouse.getStatus())
                .createdAt(warehouse.getCreatedAt())
                .updatedAt(warehouse.getUpdatedAt())
                .build();
    }

}