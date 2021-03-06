package com.farmerzharvest.ecom.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUnitPriceAddUpdateRequest {
    private Long productUnitPriceId;
    private float price;
    private Boolean isActive;
    private Long productId;
    private Long productUnitId;
}