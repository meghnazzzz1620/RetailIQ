package com.retailiq.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlySalesResponse {

    private String month;

    private BigDecimal revenue;

}