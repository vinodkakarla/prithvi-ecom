package com.farmerzharvest.ecom.dto;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    Collection<OrderProductDetail> orderDetails;
    private long accountAddressId;
    private float totalAmount;
    private String pickUp;
    private String status;
    private String slot;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderProductDetail {
        private long productId;
        private long unitId;
        private float pricePerUnit;
        private short unitQuantity;
        private short productQuantity;
        private float totalUnitAmount;
    }
}