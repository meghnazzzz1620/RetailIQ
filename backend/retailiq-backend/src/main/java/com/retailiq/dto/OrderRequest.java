package com.retailiq.dto;

import com.retailiq.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private Long employeeId;

    @NotNull
    private PaymentMethod paymentMethod;

    private BigDecimal discount;

    @NotNull
    private List<OrderItemRequest> items;

    @NotNull
    private Long warehouseId;



}