package com.farmerzharvest.ecom.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private List<InventoryItem> inventoryItems;

    @Getter
    @Setter
    @Builder
    public static class InventoryItem {
        private Long productId;
        private String productName;
        private String imageUrl;
        private String productCategory;
        private String unitType;
        private Long minQuantity;
        private float unitPrice;
        private Long totalAvailableUnits;
    }
}
