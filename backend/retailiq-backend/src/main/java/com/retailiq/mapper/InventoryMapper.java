package com.retailiq.mapper;

import com.retailiq.dto.InventoryRequest;
import com.retailiq.dto.InventoryResponse;
import com.retailiq.entity.Inventory;
import com.retailiq.entity.Product;
import com.retailiq.entity.Warehouse;

import java.time.LocalDateTime;

public class InventoryMapper {

    public static Inventory toEntity(
            InventoryRequest request,
            Product product,
            Warehouse warehouse) {

        return Inventory.builder()
                .product(product)
                .warehouse(warehouse)
                .availableQuantity(request.getAvailableQuantity())
                .reservedQuantity(request.getReservedQuantity())
                .reorderLevel(request.getReorderLevel())
                .maximumCapacity(request.getMaximumCapacity())
                .lastStockUpdated(LocalDateTime.now())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static InventoryResponse toResponse(Inventory inventory) {

        return InventoryResponse.builder()
                .inventoryId(inventory.getInventoryId())
                .productId(inventory.getProduct().getProductId())
                .productName(inventory.getProduct().getProductName())
                .warehouseId(inventory.getWarehouse().getWarehouseId())
                .warehouseName(inventory.getWarehouse().getWarehouseName())
                .availableQuantity(inventory.getAvailableQuantity())
                .reservedQuantity(inventory.getReservedQuantity())
                .reorderLevel(inventory.getReorderLevel())
                .maximumCapacity(inventory.getMaximumCapacity())
                .lastStockUpdated(inventory.getLastStockUpdated())
                .status(inventory.getStatus())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }
}