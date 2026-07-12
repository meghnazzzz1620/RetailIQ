package com.retailiq.repository;

import com.retailiq.dto.WarehouseInventoryResponse;
import com.retailiq.entity.Inventory;
import com.retailiq.entity.Product;
import com.retailiq.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.retailiq.dto.InventoryReportResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository
        extends JpaRepository<Inventory, Long> {

    List<Inventory> findByStatusTrue();

    boolean existsByProductAndWarehouse(
            Product product,
            Warehouse warehouse
    );

    Optional<Inventory> findByProductAndWarehouse(
            Product product,
            Warehouse warehouse
    );

    @Query("""
            SELECT COUNT(i)
            FROM Inventory i
            WHERE i.status = true
            AND i.availableQuantity <= i.reorderLevel
            """)
    long countByAvailableQuantityLessThanEqualReorderLevel();

    @Query("""
            SELECT COALESCE(
                SUM(i.availableQuantity * p.purchasePrice),
                0
            )
            FROM Inventory i
            JOIN i.product p
            WHERE i.status = true
            """)
    BigDecimal getInventoryValue();

    @Query("""
            SELECT i
            FROM Inventory i
            WHERE i.status = true
            AND i.availableQuantity <= i.reorderLevel
            ORDER BY i.availableQuantity ASC
            """)
    List<Inventory> getLowStockProducts();

    @Query("""
            SELECT new com.retailiq.dto.WarehouseInventoryResponse(
                i.warehouse.warehouseName,
                SUM(CAST(i.availableQuantity AS long))
            )
            FROM Inventory i
            WHERE i.status = true
            GROUP BY i.warehouse.warehouseName
            ORDER BY i.warehouse.warehouseName
            """)
    List<WarehouseInventoryResponse> getWarehouseInventory();

    @Query("""
SELECT new com.retailiq.dto.InventoryReportResponse(

i.product.productName,

i.warehouse.warehouseName,

i.availableQuantity,

i.reservedQuantity,

i.reorderLevel,

i.maximumCapacity,

CASE
WHEN i.availableQuantity <= i.reorderLevel
THEN 'Low Stock'
ELSE 'In Stock'
END

)

FROM Inventory i

WHERE i.status = true

ORDER BY i.product.productName
""")
    List<InventoryReportResponse> getInventoryReport();

}