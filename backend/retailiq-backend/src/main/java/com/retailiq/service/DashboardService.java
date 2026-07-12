package com.retailiq.service;

import com.retailiq.dto.*;
import com.retailiq.entity.OrderItem;
import com.retailiq.mapper.InventoryMapper;
import com.retailiq.mapper.OrderMapper;
import com.retailiq.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderItemRepository orderItemRepository;

    public DashboardService(
            ProductRepository productRepository,
            CustomerRepository customerRepository,
            OrderRepository orderRepository,
            InventoryRepository inventoryRepository,
            OrderItemRepository orderItemRepository) {

        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<MonthlySalesResponse> getMonthlySales() {

        return orderRepository
                .getMonthlySales()
                .stream()
                .map(data -> MonthlySalesResponse.builder()

                        .month(data.getMonth())

                        .revenue(data.getRevenue())

                        .build()

                )
                .toList();

    }

    public DashboardResponse getDashboardStats() {

        return DashboardResponse.builder()

                .totalProducts(
                        productRepository.countByStatusTrue())

                .totalCustomers(
                        customerRepository.countByStatusTrue())

                .totalOrders(
                        orderRepository.count())

                .lowStockProducts(
                        inventoryRepository
                                .countByAvailableQuantityLessThanEqualReorderLevel())

                .totalRevenue(
                        orderRepository.getTotalRevenue())

                .inventoryValue(
                        inventoryRepository.getInventoryValue())

                .build();

    }

    public List<OrderResponse> getRecentOrders() {

        return orderRepository
                .findTop5ByStatusTrueOrderByOrderDateDesc()
                .stream()
                .map(order -> {

                    List<OrderItem> items =
                            orderItemRepository.findByOrder(order);

                    return OrderMapper.toResponse(order, items);

                })
                .toList();

    }

    public List<InventoryResponse> getLowStockProducts() {

        return inventoryRepository
                .getLowStockProducts()
                .stream()
                .map(InventoryMapper::toResponse)
                .toList();

    }

    public List<TopProductResponse> getTopSellingProducts() {

        return orderItemRepository.getTopSellingProducts();

    }

    public List<WeeklyOrderResponse> getWeeklyOrders() {

        return orderRepository
                .getWeeklyOrders()
                .stream()
                .map(data -> WeeklyOrderResponse.builder()

                        .day(data.getDay())

                        .totalOrders(data.getOrders())

                        .build()

                )
                .toList();

    }

    public List<WarehouseInventoryResponse> getWarehouseInventory() {

        return inventoryRepository.getWarehouseInventory();

    }
}