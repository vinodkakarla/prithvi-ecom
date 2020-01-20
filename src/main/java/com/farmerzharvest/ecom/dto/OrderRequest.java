package com.farmerzharvest.ecom.dto;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private long accountAddressId;
    private float totalAmount;
    Collection<OrderProductDetail> orderDetails;

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
        private float totalUnitAmount;
    }
}