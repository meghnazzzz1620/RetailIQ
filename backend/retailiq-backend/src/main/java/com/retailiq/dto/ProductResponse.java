package com.retailiq.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;

    private String sku;

    private String barcode;

    private String productName;

    private String description;

    private BigDecimal purchasePrice;

    private BigDecimal sellingPrice;

    private BigDecimal profitMargin;

    private BigDecimal gstPercentage;

    private Integer reorderLevel;

    private Integer safetyStock;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}