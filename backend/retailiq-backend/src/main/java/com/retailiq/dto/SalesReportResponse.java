package com.retailiq.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReportResponse {

    private String orderNumber;

    private String customerName;

    private String employeeName;

    private String warehouseName;

    private BigDecimal grandTotal;

    private String orderStatus;

    private LocalDateTime orderDate;

}