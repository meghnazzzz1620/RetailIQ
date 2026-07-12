package com.retailiq.repository;

import com.retailiq.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByBrandName(String brandName);

    List<Brand> findByStatusTrue();

}