package com.farmerzharvest.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddUpdateRequest {
    private Long productId;
    private String name;
    private String description;
    private String imageUrl;
    private Boolean isActive;
    private Long categoryId;
    private Long inventoryId;
}
