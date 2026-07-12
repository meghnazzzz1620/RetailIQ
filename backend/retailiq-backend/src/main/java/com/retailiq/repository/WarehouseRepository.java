package com.retailiq.repository;

import com.retailiq.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    boolean existsByWarehouseCode(String warehouseCode);

    List<Warehouse> findByStatusTrue();
    Optional<Warehouse> findById(Long warehouseId);

}