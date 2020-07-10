package com.farmerzharvest.ecom.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private Float totalQuantity;
    private boolean isActive;
    private Collection<UnitDetail> unitDetails;

    @Setter
    @Getter
    @Builder
    public static class UnitDetail {
        private Long id;
        private String unitType;
        private Long unitQuantity;
        private Long unitId;
        private float pricePerUnit;
    }
}

