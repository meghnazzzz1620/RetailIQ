package com.retailiq.service;

import com.retailiq.dto.InventoryRequest;
import com.retailiq.dto.InventoryResponse;
import com.retailiq.entity.Inventory;
import com.retailiq.entity.Product;
import com.retailiq.entity.Warehouse;
import com.retailiq.exception.BusinessException;
import com.retailiq.mapper.InventoryMapper;
import com.retailiq.repository.InventoryRepository;
import com.retailiq.repository.ProductRepository;
import com.retailiq.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public InventoryResponse createInventory(InventoryRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new BusinessException("Product not found."));

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() ->
                        new BusinessException("Warehouse not found."));

        if (inventoryRepository.existsByProductAndWarehouse(product, warehouse)) {
            throw new BusinessException(
                    "Inventory already exists for this Product and Warehouse.");
        }

        Inventory inventory =
                InventoryMapper.toEntity(request, product, warehouse);

        Inventory savedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toResponse(savedInventory);
    }

    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findByStatusTrue()
                .stream()
                .map(InventoryMapper::toResponse)
                .toList();
    }
    public InventoryResponse getInventoryById(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Inventory not found."));

        return InventoryMapper.toResponse(inventory);

    }

    public InventoryResponse updateInventory(
            Long id,
            InventoryRequest request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Inventory not found."));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new BusinessException("Product not found."));

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() ->
                        new BusinessException("Warehouse not found."));

        inventory.setProduct(product);
        inventory.setWarehouse(warehouse);
        inventory.setAvailableQuantity(request.getAvailableQuantity());
        inventory.setReservedQuantity(request.getReservedQuantity());
        inventory.setReorderLevel(request.getReorderLevel());
        inventory.setMaximumCapacity(request.getMaximumCapacity());

        inventory.setLastStockUpdated(java.time.LocalDateTime.now());
        inventory.setUpdatedAt(java.time.LocalDateTime.now());

        Inventory updated = inventoryRepository.save(inventory);

        return InventoryMapper.toResponse(updated);

    }

    public void deleteInventory(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Inventory not found."));

        inventory.setStatus(false);

        inventory.setUpdatedAt(java.time.LocalDateTime.now());

        inventoryRepository.save(inventory);

    }

}