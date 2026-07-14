package com.retailiq.repository;

import com.retailiq.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByBrandName(String brandName);

    List<Brand> findByStatusTrue();

    @Query("""
            SELECT b
            FROM Brand b
            WHERE b.status = true
            AND (
                LOWER(b.brandName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(b.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
            )
            """)
    List<Brand> searchBrands(@Param("keyword") String keyword);

}