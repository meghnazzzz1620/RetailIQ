package com.retailiq.repository;
import com.retailiq.projection.MonthlySalesProjection;
import com.retailiq.projection.WeeklyOrderProjection;
import com.retailiq.dto.MonthlySalesResponse;
import com.retailiq.dto.SalesReportResponse;
import com.retailiq.dto.WeeklyOrderResponse;
import com.retailiq.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByOrderNumber(String orderNumber);

    List<Order> findAll();

    long count();

    @Query("""
            SELECT COALESCE(SUM(o.grandTotal),0)
            FROM Order o
            """)
    BigDecimal getTotalRevenue();

    @Query(value = """
SELECT

MONTHNAME(order_date) AS month,

SUM(grand_total) AS revenue

FROM orders

GROUP BY MONTH(order_date), MONTHNAME(order_date)

ORDER BY MONTH(order_date)

""", nativeQuery = true)
    List<MonthlySalesProjection> getMonthlySales();

    List<Order> findTop5ByStatusTrueOrderByOrderDateDesc();

    @Query(value = """
SELECT

DAYNAME(order_date) AS day,

COUNT(*) AS orders

FROM orders

WHERE order_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)

GROUP BY DAYOFWEEK(order_date), DAYNAME(order_date)

ORDER BY DAYOFWEEK(order_date)

""", nativeQuery = true)
    List<WeeklyOrderProjection> getWeeklyOrders();

    @Query("""
SELECT new com.retailiq.dto.SalesReportResponse(

o.orderNumber,

CONCAT(o.customer.firstName,' ',o.customer.lastName),

CONCAT(o.employee.firstName,' ',o.employee.lastName),

o.warehouse.warehouseName,

o.grandTotal,

CAST(o.orderStatus AS string),

o.orderDate

)

FROM Order o

ORDER BY o.orderDate DESC
""")
    List<SalesReportResponse> getSalesReport();

    @Query("""
SELECT new com.retailiq.dto.SalesReportResponse(

o.orderNumber,

CONCAT(o.customer.firstName,' ',o.customer.lastName),

CONCAT(o.employee.firstName,' ',o.employee.lastName),

o.warehouse.warehouseName,

o.grandTotal,

CAST(o.orderStatus AS string),

o.orderDate

)

FROM Order o

WHERE o.orderDate BETWEEN :startDate AND :endDate

ORDER BY o.orderDate DESC
""")
    List<SalesReportResponse> getSalesReportByDate(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("""
SELECT new com.retailiq.dto.SalesReportResponse(

o.orderNumber,

CONCAT(o.customer.firstName,' ',o.customer.lastName),

CONCAT(o.employee.firstName,' ',o.employee.lastName),

o.warehouse.warehouseName,

o.grandTotal,

CAST(o.orderStatus AS string),

o.orderDate

)

FROM Order o

WHERE

LOWER(o.orderNumber) LIKE LOWER(CONCAT('%',:keyword,'%'))

OR LOWER(CONCAT(o.customer.firstName,' ',o.customer.lastName))

LIKE LOWER(CONCAT('%',:keyword,'%'))

ORDER BY o.orderDate DESC
""")
    List<SalesReportResponse> searchSalesReport(
            @Param("keyword") String keyword
    );
}