package com.retailiq.mapper;

import com.retailiq.dto.*;
import com.retailiq.entity.Brand;

import java.time.LocalDateTime;

public class BrandMapper {

    public static Brand toEntity(BrandRequest request){

        return Brand.builder()
                .brandName(request.getBrandName())
                .description(request.getDescription())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static BrandResponse toResponse(Brand brand){

        return BrandResponse.builder()
                .brandId(brand.getBrandId())
                .brandName(brand.getBrandName())
                .description(brand.getDescription())
                .status(brand.getStatus())
                .createdAt(brand.getCreatedAt())
                .updatedAt(brand.getUpdatedAt())
                .build();
    }

}