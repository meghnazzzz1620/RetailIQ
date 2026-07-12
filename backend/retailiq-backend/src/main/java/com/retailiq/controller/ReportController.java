package com.retailiq.controller;
import com.retailiq.dto.SupplierReportResponse;
import com.retailiq.dto.SalesReportResponse;
import com.retailiq.service.ReportService;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.retailiq.dto.InventoryReportResponse;
@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {

        this.reportService = reportService;

    }

    @GetMapping("/sales")
    public List<SalesReportResponse> getSalesReport() {

        return reportService.getSalesReport();

    }
    @GetMapping("/sales/filter")
    public List<SalesReportResponse> getSalesReportByDate(

            @RequestParam LocalDateTime start,

            @RequestParam LocalDateTime end) {

        return reportService.getSalesReportByDate(start, end);

    }

    @GetMapping("/sales/search")
    public List<SalesReportResponse> searchSalesReport(

            @RequestParam String keyword) {

        return reportService.searchSalesReport(keyword);

    }

    @GetMapping("/inventory")
    public List<InventoryReportResponse> getInventoryReport() {

        return reportService.getInventoryReport();

    }

    @GetMapping("/suppliers")
    public List<SupplierReportResponse> getSupplierReport() {

        return reportService.getSupplierReport();

    }
}