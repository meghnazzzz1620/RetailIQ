package com.retailiq.controller;

import com.retailiq.dto.WarehouseRequest;
import com.retailiq.dto.WarehouseResponse;
import com.retailiq.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public WarehouseResponse createWarehouse(
            @Valid @RequestBody WarehouseRequest request) {

        return warehouseService.createWarehouse(request);
    }

    @GetMapping
    public List<WarehouseResponse> getAllWarehouses() {

        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public WarehouseResponse getWarehouseById(@PathVariable Long id) {

        return warehouseService.getWarehouseById(id);

    }

    @PutMapping("/{id}")
    public WarehouseResponse updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseRequest request) {

        return warehouseService.updateWarehouse(id, request);

    }

    @DeleteMapping("/{id}")
    public String deleteWarehouse(@PathVariable Long id) {

        warehouseService.deleteWarehouse(id);

        return "Warehouse deleted successfully.";

    }
}