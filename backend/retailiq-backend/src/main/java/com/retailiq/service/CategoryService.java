package com.retailiq.service;

import com.retailiq.dto.CategoryRequest;
import com.retailiq.dto.CategoryResponse;
import com.retailiq.entity.Category;
import com.retailiq.exception.BusinessException;
import com.retailiq.mapper.CategoryMapper;
import com.retailiq.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(CategoryRequest request) {

        if(categoryRepository.existsByCategoryName(request.getCategoryName())) {
            throw new BusinessException("Category already exists.");
        }

        Category category = CategoryMapper.toEntity(request);

        Category saved = categoryRepository.save(category);

        return CategoryMapper.toResponse(saved);
    }

    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findByStatusTrue()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Category not found."));

        return CategoryMapper.toResponse(category);

    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Category not found."));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setUpdatedAt(java.time.LocalDateTime.now());

        Category updated = categoryRepository.save(category);

        return CategoryMapper.toResponse(updated);

    }

    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Category not found."));

        category.setStatus(false);

        category.setUpdatedAt(java.time.LocalDateTime.now());

        categoryRepository.save(category);

    }
}