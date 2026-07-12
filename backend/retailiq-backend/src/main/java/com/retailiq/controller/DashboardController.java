package com.retailiq.controller;

import com.retailiq.dto.DashboardResponse;
import com.retailiq.service.DashboardService;

import org.springframework.web.bind.annotation.*;
import com.retailiq.dto.MonthlySalesResponse;

import java.util.List;
import com.retailiq.dto.OrderResponse;
import com.retailiq.dto.InventoryResponse;
import com.retailiq.dto.TopProductResponse;
import java.util.List;
import com.retailiq.dto.WeeklyOrderResponse;
import com.retailiq.dto.WarehouseInventoryResponse;
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;

    }

    @GetMapping
    public DashboardResponse getDashboard() {

        return dashboardService.getDashboardStats();

    }
    @GetMapping("/monthly-sales")
    public List<MonthlySalesResponse> getMonthlySales() {

        return dashboardService.getMonthlySales();

    }
    @GetMapping("/recent-orders")
    public List<OrderResponse> getRecentOrders() {

        return dashboardService.getRecentOrders();

    }

    @GetMapping("/low-stock")
    public List<InventoryResponse> getLowStockProducts() {

        return dashboardService.getLowStockProducts();

    }

    @GetMapping("/top-products")
    public List<TopProductResponse> getTopSellingProducts() {

        return dashboardService.getTopSellingProducts();

    }
    @GetMapping("/weekly-orders")
    public List<WeeklyOrderResponse> getWeeklyOrders() {

        return dashboardService.getWeeklyOrders();

    }
    @GetMapping("/warehouse-inventory")
    public List<WarehouseInventoryResponse> getWarehouseInventory() {

        return dashboardService.getWarehouseInventory();

    }
}