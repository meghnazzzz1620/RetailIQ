package com.retailiq.service;
import com.retailiq.repository.SupplierRepository;
import com.retailiq.dto.SupplierReportResponse;
import com.retailiq.dto.InventoryReportResponse;
import com.retailiq.dto.SalesReportResponse;
import com.retailiq.repository.InventoryRepository;
import com.retailiq.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final SupplierRepository supplierRepository;

    public ReportService(
            OrderRepository orderRepository,
            InventoryRepository inventoryRepository,
            SupplierRepository supplierRepository) {

        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<SalesReportResponse> getSalesReport() {

        return orderRepository.getSalesReport();

    }

    public List<SalesReportResponse> getSalesReportByDate(
            LocalDateTime start,
            LocalDateTime end) {

        return orderRepository.getSalesReportByDate(start, end);

    }

    public List<SalesReportResponse> searchSalesReport(
            String keyword) {

        return orderRepository.searchSalesReport(keyword);

    }

    public List<InventoryReportResponse> getInventoryReport() {

        return inventoryRepository.getInventoryReport();

    }
    public List<SupplierReportResponse> getSupplierReport() {

        return supplierRepository.getSupplierReport();

    }

}