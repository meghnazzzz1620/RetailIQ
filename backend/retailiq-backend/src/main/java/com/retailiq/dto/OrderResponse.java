package com.retailiq.dto;

import com.retailiq.entity.OrderStatus;
import com.retailiq.entity.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;

    private String orderNumber;

    private Long customerId;

    private String customerName;

    private Long employeeId;

    private String employeeName;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    private BigDecimal subtotal;

    private BigDecimal gstAmount;

    private BigDecimal discount;

    private BigDecimal grandTotal;

    private LocalDateTime orderDate;

    private List<OrderItemResponse> items;

}