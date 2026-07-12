package com.retailiq.mapper;

import com.retailiq.dto.OrderItemResponse;
import com.retailiq.dto.OrderResponse;
import com.retailiq.entity.Order;
import com.retailiq.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponse toResponse(Order order, List<OrderItem> orderItems) {

        List<OrderItemResponse> items = orderItems.stream()
                .map(item -> OrderItemResponse.builder()
                        .orderItemId(item.getOrderItemId())
                        .productId(item.getProduct().getProductId())
                        .productName(item.getProduct().getProductName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .gstPercentage(item.getGstPercentage())
                        .discount(item.getDiscount())
                        .totalPrice(item.getTotalPrice())
                        .build())
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderNumber(order.getOrderNumber())

                .customerId(order.getCustomer().getCustomerId())
                .customerName(
                        order.getCustomer().getFirstName() + " " +
                                order.getCustomer().getLastName())

                .employeeId(order.getEmployee().getEmployeeId())
                .employeeName(
                        order.getEmployee().getFirstName() + " " +
                                order.getEmployee().getLastName())

                .orderStatus(order.getOrderStatus())
                .paymentMethod(order.getPaymentMethod())

                .subtotal(order.getSubtotal())
                .gstAmount(order.getGstAmount())
                .discount(order.getDiscount())
                .grandTotal(order.getGrandTotal())

                .orderDate(order.getOrderDate())

                .items(items)

                .build();
    }

}