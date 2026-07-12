package com.retailiq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryReportResponse {

    private String productName;

    private String warehouseName;

    private Integer availableQuantity;

    private Integer reservedQuantity;

    private Integer reorderLevel;

    private Integer maximumCapacity;

    private String status;

}