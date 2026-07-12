package com.retailiq.controller;
import jakarta.validation.Valid;
import com.retailiq.dto.ProductRequest;
import com.retailiq.dto.ProductResponse;
import com.retailiq.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody ProductRequest request) {
        return productService.saveProduct(request);
    }
    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return productService.updateProduct(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully.";

    }
    @GetMapping("/search")
    public List<ProductResponse> searchProducts(
            @RequestParam String keyword) {

        return productService.searchProducts(keyword);

    }
    @GetMapping("/page")
    public Page<ProductResponse> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return productService.getProducts(page, size);

    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {

        return productService.getProductById(id);

    }
    @GetMapping("/sorted")
    public Page<ProductResponse> getSortedProducts(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "productName") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return productService.getProducts(
                page,
                size,
                sortBy,
                direction
        );
    }
}