package com.retailiq.repository;

import com.retailiq.dto.TopProductResponse;
import com.retailiq.entity.Order;
import com.retailiq.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);

    @Query("""
    SELECT new com.retailiq.dto.TopProductResponse(
        oi.product.productId,
        oi.product.productName,
        SUM(CAST(oi.quantity AS long))
    )
    FROM OrderItem oi
    GROUP BY oi.product.productId, oi.product.productName
    ORDER BY SUM(oi.quantity) DESC
    """)
    List<TopProductResponse> getTopSellingProducts();

}