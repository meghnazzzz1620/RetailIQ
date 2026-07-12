package com.retailiq.mapper;

import com.retailiq.dto.ProductRequest;
import com.retailiq.dto.ProductResponse;
import com.retailiq.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductMapper {

    public static Product toEntity(ProductRequest request) {

        Product product = new Product();

        product.setSku(request.getSku());
        product.setBarcode(request.getBarcode());
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());

        product.setPurchasePrice(request.getPurchasePrice());
        product.setSellingPrice(request.getSellingPrice());

        product.setGstPercentage(request.getGstPercentage());

        product.setReorderLevel(request.getReorderLevel());
        product.setSafetyStock(request.getSafetyStock());

        product.setStatus(true);

        product.setCreatedAt(LocalDateTime.now());

        product.setUpdatedAt(LocalDateTime.now());

        if (request.getPurchasePrice() != null &&
                request.getSellingPrice() != null &&
                request.getPurchasePrice().compareTo(BigDecimal.ZERO) > 0) {

            BigDecimal profit = request.getSellingPrice()
                    .subtract(request.getPurchasePrice());

            BigDecimal margin = profit
                    .multiply(BigDecimal.valueOf(100))
                    .divide(request.getPurchasePrice(), 2, java.math.RoundingMode.HALF_UP);

            product.setProfitMargin(margin);
        }

        return product;
    }

    public static ProductResponse toResponse(Product product) {

        return ProductResponse.builder()
                .productId(product.getProductId())
                .sku(product.getSku())
                .barcode(product.getBarcode())
                .productName(product.getProductName())
                .description(product.getDescription())
                .purchasePrice(product.getPurchasePrice())
                .sellingPrice(product.getSellingPrice())
                .profitMargin(product.getProfitMargin())
                .gstPercentage(product.getGstPercentage())
                .reorderLevel(product.getReorderLevel())
                .safetyStock(product.getSafetyStock())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}