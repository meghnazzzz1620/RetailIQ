package com.retailiq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseInventoryResponse {

    private String warehouseName;

    private Long totalQuantity;

}