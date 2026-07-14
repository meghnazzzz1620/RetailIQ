package com.retailiq.controller;

import com.retailiq.dto.BrandRequest;
import com.retailiq.dto.BrandResponse;
import com.retailiq.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public BrandResponse createBrand(
            @Valid @RequestBody BrandRequest request) {

        return brandService.createBrand(request);
    }

    @GetMapping
    public List<BrandResponse> getAllBrands() {

        return brandService.getAllBrands();
    }

    @GetMapping("/search")
    public List<BrandResponse> searchBrands(
            @RequestParam String keyword) {

        return brandService.searchBrands(keyword);

    }

    @GetMapping("/{id}")
    public BrandResponse getBrandById(@PathVariable Long id) {

        return brandService.getBrandById(id);

    }

    @PutMapping("/{id}")
    public BrandResponse updateBrand(
            @PathVariable Long id,
            @Valid @RequestBody BrandRequest request) {

        return brandService.updateBrand(id, request);

    }

    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable Long id) {

        brandService.deleteBrand(id);

        return "Brand deleted successfully.";

    }


}