package com.retailiq.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private Long totalProducts;

    private Long totalCustomers;

    private Long totalOrders;

    private Long lowStockProducts;

    private BigDecimal totalRevenue;

    private BigDecimal inventoryValue;

}