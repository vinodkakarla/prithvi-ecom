package com.farmerzharvest.ecom.dto;

import lombok.*;

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
    private long accountAddressId;
    private float totalAmount;
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


//    private long accountAddressId;
//    private float totalAmount;
//    Collection<OrderProductDetail> orderDetails;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public static class OrderProductDetail {
//    private long productId;
//    private long unitId;
//    private float pricePerUnit;
//    private short unitQuantity;
//    private float totalUnitAmount;
//}