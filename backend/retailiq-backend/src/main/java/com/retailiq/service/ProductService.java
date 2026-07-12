package com.retailiq.service;

import com.retailiq.dto.ProductRequest;
import com.retailiq.dto.ProductResponse;
import java.time.LocalDateTime;
import org.springframework.data.domain.Sort;
import com.retailiq.entity.Product;
import com.retailiq.mapper.ProductMapper;
import com.retailiq.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.retailiq.exception.BusinessException;
import com.retailiq.exception.DuplicateBarcodeException;
import com.retailiq.exception.DuplicateSkuException;
import com.retailiq.exception.InvalidPriceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse saveProduct(ProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateSkuException("SKU already exists.");
        }

        if (request.getBarcode() != null
                && !request.getBarcode().isBlank()
                && productRepository.existsByBarcode(request.getBarcode())) {

            throw new DuplicateBarcodeException("Barcode already exists.");
        }

        if (request.getSellingPrice()
                .compareTo(request.getPurchasePrice()) <= 0) {

            throw new InvalidPriceException(
                    "Selling Price must be greater than Purchase Price.");
        }
        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {

        return productRepository.findByStatusTrue()
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Product not found."));

        if (!product.getSku().equals(request.getSku())
                && productRepository.existsBySku(request.getSku())) {

            throw new DuplicateSkuException("SKU already exists.");
        }

        if (request.getBarcode() != null
                && !request.getBarcode().isBlank()) {

            if (!request.getBarcode().equals(product.getBarcode())
                    && productRepository.existsByBarcode(request.getBarcode())) {

                throw new DuplicateBarcodeException("Barcode already exists.");
            }
        }

        if (request.getSellingPrice()
                .compareTo(request.getPurchasePrice()) <= 0) {

            throw new InvalidPriceException(
                    "Selling Price must be greater than Purchase Price.");
        }

        product.setSku(request.getSku());
        product.setBarcode(request.getBarcode());
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());

        product.setPurchasePrice(request.getPurchasePrice());
        product.setSellingPrice(request.getSellingPrice());

        product.setGstPercentage(request.getGstPercentage());

        product.setReorderLevel(request.getReorderLevel());
        product.setSafetyStock(request.getSafetyStock());

        product.setUpdatedAt(java.time.LocalDateTime.now());

        Product updated = productRepository.save(product);

        return ProductMapper.toResponse(updated);
    }
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Product not found."));

        product.setStatus(false);

        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);

    }
    public List<ProductResponse> searchProducts(String keyword) {

        return productRepository.searchProducts(keyword)
                .stream()
                .map(ProductMapper::toResponse)
                .toList();

    }
    public Page<ProductResponse> getProducts(int page, int size) {

        return productRepository
                .findByStatusTrue(PageRequest.of(page, size))
                .map(ProductMapper::toResponse);

    }
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Product not found."));

        return ProductMapper.toResponse(product);

    }
    public Page<ProductResponse> getProducts(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return productRepository
                .findByStatusTrue(PageRequest.of(page, size, sort))
                .map(ProductMapper::toResponse);
    }

}