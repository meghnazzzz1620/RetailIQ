package com.retailiq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopProductResponse {

    private Long productId;

    private String productName;

    private Long quantitySold;

}