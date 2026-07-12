package com.retailiq.service;

import com.retailiq.dto.WarehouseRequest;
import com.retailiq.dto.WarehouseResponse;
import com.retailiq.entity.Warehouse;
import com.retailiq.exception.BusinessException;
import com.retailiq.mapper.WarehouseMapper;
import com.retailiq.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public WarehouseResponse createWarehouse(WarehouseRequest request) {

        if (warehouseRepository.existsByWarehouseCode(request.getWarehouseCode())) {
            throw new BusinessException("Warehouse Code already exists.");
        }

        Warehouse warehouse = WarehouseMapper.toEntity(request);

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        return WarehouseMapper.toResponse(savedWarehouse);
    }

    public List<WarehouseResponse> getAllWarehouses() {

        return warehouseRepository.findByStatusTrue()
                .stream()
                .map(WarehouseMapper::toResponse)
                .toList();
    }
    public WarehouseResponse getWarehouseById(Long id) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Warehouse not found."));

        return WarehouseMapper.toResponse(warehouse);

    }

    public WarehouseResponse updateWarehouse(Long id, WarehouseRequest request) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Warehouse not found."));

        warehouse.setWarehouseCode(request.getWarehouseCode());
        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setManagerName(request.getManagerName());
        warehouse.setPhone(request.getPhone());
        warehouse.setEmail(request.getEmail());
        warehouse.setAddress(request.getAddress());
        warehouse.setCity(request.getCity());
        warehouse.setState(request.getState());
        warehouse.setCountry(request.getCountry());
        warehouse.setPostalCode(request.getPostalCode());

        warehouse.setUpdatedAt(java.time.LocalDateTime.now());

        Warehouse updated = warehouseRepository.save(warehouse);

        return WarehouseMapper.toResponse(updated);

    }

    public void deleteWarehouse(Long id) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Warehouse not found."));

        warehouse.setStatus(false);

        warehouse.setUpdatedAt(java.time.LocalDateTime.now());

        warehouseRepository.save(warehouse);

    }

}