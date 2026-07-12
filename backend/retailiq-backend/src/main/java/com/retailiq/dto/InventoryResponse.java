package com.retailiq.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private Long inventoryId;

    private Long productId;

    private String productName;

    private Long warehouseId;

    private String warehouseName;

    private Integer availableQuantity;

    private Integer reservedQuantity;

    private Integer reorderLevel;

    private Integer maximumCapacity;

    private LocalDateTime lastStockUpdated;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}