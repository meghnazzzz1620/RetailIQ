package com.retailiq.mapper;

import com.retailiq.dto.CategoryRequest;
import com.retailiq.dto.CategoryResponse;
import com.retailiq.entity.Category;

import java.time.LocalDateTime;

public class CategoryMapper {

    public static Category toEntity(CategoryRequest request) {

        return Category.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CategoryResponse toResponse(Category category) {

        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .status(category.getStatus())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}