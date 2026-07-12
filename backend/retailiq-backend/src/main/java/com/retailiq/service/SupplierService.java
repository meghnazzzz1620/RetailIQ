package com.retailiq.service;

import com.retailiq.dto.SupplierRequest;
import com.retailiq.dto.SupplierResponse;
import com.retailiq.entity.Supplier;
import com.retailiq.exception.BusinessException;
import com.retailiq.mapper.SupplierMapper;
import com.retailiq.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierResponse createSupplier(SupplierRequest request) {

        // Business Rule 1
        if (supplierRepository.existsBySupplierCode(request.getSupplierCode())) {
            throw new BusinessException("Supplier Code already exists.");
        }

        // Business Rule 2
        if (request.getEmail() != null &&
                !request.getEmail().isBlank() &&
                supplierRepository.existsByEmail(request.getEmail())) {

            throw new BusinessException("Email already exists.");
        }

        Supplier supplier = SupplierMapper.toEntity(request);

        Supplier savedSupplier = supplierRepository.save(supplier);

        return SupplierMapper.toResponse(savedSupplier);
    }

    public List<SupplierResponse> getAllSuppliers() {

        return supplierRepository.findByStatusTrue()
                .stream()
                .map(SupplierMapper::toResponse)
                .toList();
    }
    public SupplierResponse getSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Supplier not found."));

        return SupplierMapper.toResponse(supplier);

    }

    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Supplier not found."));

        supplier.setSupplierCode(request.getSupplierCode());
        supplier.setCompanyName(request.getCompanyName());
        supplier.setGstNumber(request.getGstNumber());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setStreet(request.getStreet());
        supplier.setCity(request.getCity());
        supplier.setState(request.getState());
        supplier.setCountry(request.getCountry());
        supplier.setPostalCode(request.getPostalCode());

        supplier.setUpdatedAt(java.time.LocalDateTime.now());

        Supplier updated = supplierRepository.save(supplier);

        return SupplierMapper.toResponse(updated);

    }

    public void deleteSupplier(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Supplier not found."));

        supplier.setStatus(false);
        supplier.setUpdatedAt(java.time.LocalDateTime.now());

        supplierRepository.save(supplier);

    }

}