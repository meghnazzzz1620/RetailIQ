package com.retailiq.service;

import com.retailiq.dto.BrandRequest;
import com.retailiq.dto.BrandResponse;
import com.retailiq.entity.Brand;
import com.retailiq.exception.BusinessException;
import com.retailiq.mapper.BrandMapper;
import com.retailiq.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandResponse createBrand(BrandRequest request) {

        if (brandRepository.existsByBrandName(request.getBrandName())) {
            throw new BusinessException("Brand already exists.");
        }

        Brand brand = BrandMapper.toEntity(request);

        Brand savedBrand = brandRepository.save(brand);

        return BrandMapper.toResponse(savedBrand);
    }

    public List<BrandResponse> getAllBrands() {

        return brandRepository.findByStatusTrue()
                .stream()
                .map(BrandMapper::toResponse)
                .toList();
    }
    public BrandResponse getBrandById(Long id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Brand not found."));

        return BrandMapper.toResponse(brand);

    }

    public BrandResponse updateBrand(Long id, BrandRequest request) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Brand not found."));

        brand.setBrandName(request.getBrandName());
        brand.setDescription(request.getDescription());
        brand.setUpdatedAt(java.time.LocalDateTime.now());

        Brand updated = brandRepository.save(brand);

        return BrandMapper.toResponse(updated);

    }

    public void deleteBrand(Long id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Brand not found."));

        brand.setStatus(false);
        brand.setUpdatedAt(java.time.LocalDateTime.now());

        brandRepository.save(brand);

    }
}