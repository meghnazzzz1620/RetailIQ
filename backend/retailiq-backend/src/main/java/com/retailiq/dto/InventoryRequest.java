package com.retailiq.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Long warehouseId;

    @Min(value = 0)
    private Integer availableQuantity;

    @Min(value = 0)
    private Integer reservedQuantity;

    @Min(value = 0)
    private Integer reorderLevel;

    @Min(value = 1)
    private Integer maximumCapacity;

}