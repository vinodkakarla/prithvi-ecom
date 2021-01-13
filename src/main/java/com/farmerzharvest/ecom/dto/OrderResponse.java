package com.farmerzharvest.ecom.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private String userName;
    private String orderAddress;
    private LocalDateTime orderDate;
    private long accountAddressId;
    private float totalAmount;
    private String pickUp;
    private String status;
    private Collection<OrderProductDetail> orderProductDetails;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderProductDetail {
        private long productId;
        private String productName;
        private long unitId;
        private long unitQuantity;
        private String unitType;
        private float pricePerUnit;
        private short productQuantity;
        private float totalUnitAmount;
    }
}
