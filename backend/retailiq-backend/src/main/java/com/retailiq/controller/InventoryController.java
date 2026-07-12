package com.retailiq.controller;

import com.retailiq.dto.InventoryRequest;
import com.retailiq.dto.InventoryResponse;
import com.retailiq.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public InventoryResponse createInventory(
            @Valid @RequestBody InventoryRequest request) {

        return inventoryService.createInventory(request);
    }

    @GetMapping
    public List<InventoryResponse> getAllInventory() {

        return inventoryService.getAllInventory();
    }

}