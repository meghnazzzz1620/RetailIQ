package com.retailiq.repository;

import com.retailiq.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product>  {

    boolean existsBySku(String sku);

    boolean existsByBarcode(String barcode);
    List<Product> findByStatusTrue();
    @Query("""
SELECT p
FROM Product p
WHERE p.status = true
AND (
LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))
OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :keyword, '%'))
)
""")
    List<Product> searchProducts(@Param("keyword") String keyword);
    Page<Product> findByStatusTrue(Pageable pageable);
    Optional<Product> findById(Long productId);
    long countByStatusTrue();
}