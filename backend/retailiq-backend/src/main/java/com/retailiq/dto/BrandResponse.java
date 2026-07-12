package com.retailiq.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private Long brandId;

    private String brandName;

    private String description;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}