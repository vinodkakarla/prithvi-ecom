package com.farmerzharvest.ecom.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUnitDTO {
    private Long productUnitId;
    private Long unitQuantity;
    private String unitType;
}
