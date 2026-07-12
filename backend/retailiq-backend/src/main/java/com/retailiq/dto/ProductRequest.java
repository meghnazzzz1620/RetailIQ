package com.retailiq.dto;

import lombok.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "SKU is required")
    private String sku;

    private String barcode;

    @NotBlank(message = "Product Name is required")
    private String productName;

    private String description;

    @NotNull(message = "Purchase Price is required")
    @Positive(message = "Purchase Price must be greater than zero")
    private BigDecimal purchasePrice;

    @NotNull(message = "Selling Price is required")
    @Positive(message = "Selling Price must be greater than zero")
    private BigDecimal sellingPrice;

    @NotNull(message = "GST Percentage is required")
    @DecimalMin(value = "0.0", message = "GST cannot be negative")
    private BigDecimal gstPercentage;

    @NotNull(message = "Reorder Level is required")
    @Positive(message = "Reorder Level must be greater than zero")
    private Integer reorderLevel;

    @NotNull(message = "Safety Stock is required")
    @Positive(message = "Safety Stock must be greater than zero")
    private Integer safetyStock;

}