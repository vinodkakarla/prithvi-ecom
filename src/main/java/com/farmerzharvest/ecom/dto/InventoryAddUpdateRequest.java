package com.farmerzharvest.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryAddUpdateRequest {
    private Long inventoryId;
    private String type;
    private Float totalAvailableUnits;
    private Boolean isActive;
}
