package com.retailiq.controller;

import com.retailiq.dto.SupplierRequest;
import com.retailiq.dto.SupplierResponse;
import com.retailiq.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public SupplierResponse createSupplier(
            @Valid @RequestBody SupplierRequest request) {

        return supplierService.createSupplier(request);
    }

    @GetMapping
    public List<SupplierResponse> getAllSuppliers() {

        return supplierService.getAllSuppliers();
    }
    @GetMapping("/search")
    public List<SupplierResponse> searchSuppliers(
            @RequestParam String keyword) {

        return supplierService.searchSuppliers(keyword);

    }
    @GetMapping("/{id}")
    public SupplierResponse getSupplierById(@PathVariable Long id) {

        return supplierService.getSupplierById(id);

    }

    @PutMapping("/{id}")
    public SupplierResponse updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequest request) {

        return supplierService.updateSupplier(id, request);

    }

    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable Long id) {

        supplierService.deleteSupplier(id);

        return "Supplier deleted successfully.";

    }
}