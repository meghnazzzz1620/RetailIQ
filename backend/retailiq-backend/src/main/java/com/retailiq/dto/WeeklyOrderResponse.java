package com.retailiq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeeklyOrderResponse {

    private String day;

    private Long totalOrders;

}