package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.OrderResponse;
import com.farmerzharvest.ecom.model.accounts.AccountAddress;
import com.farmerzharvest.ecom.model.order.OrderDetails;
import com.farmerzharvest.ecom.model.order.Orders;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class OrderResponseMapper {

    public OrderResponse mapOrderToResponse(Orders order) {
        OrderResponse response = OrderResponse.builder()
                .accountAddressId(order.getAccountAddress().getId())
                .orderAddress(getorderAddress(order.getAccountAddress()))
                .userName(order.getAccount().getUsername())
                .orderId(order.getId())
                .totalAmount(order.getTotalAmount())
                .orderProductDetails(getOrderProductDetails(order))
                .build();

        return response;
    }

    private Collection<OrderResponse.OrderProductDetail> getOrderProductDetails(Orders order) {
        List<OrderResponse.OrderProductDetail> orderProductDetails = Lists.newArrayList();
        for(OrderDetails orderDetail: order.getOrderDetails()) {
            OrderResponse.OrderProductDetail orderProductDetail = OrderResponse.OrderProductDetail.builder()
                    .productId(orderDetail.getProduct().getId())
                    .productName(orderDetail.getProduct().getName())
                    .unitId(orderDetail.getUnit().getId())
                    .unitQuantity(orderDetail.getUnit().getUnitQuantity())
                    .unitType(orderDetail.getUnit().getUnitType())
                    .productQuantity(orderDetail.getUnitQuantity())
                    .totalUnitAmount(orderDetail.getTotalUnitAmount())
                    .pricePerUnit(orderDetail.getPricePerUnit())
                    .build();
            orderProductDetails.add(orderProductDetail);
        }
        return orderProductDetails;
    }

    private String getorderAddress(AccountAddress address) {
        return address.getAddressLine1() + "\n" + address.getAddressLine1() + "\n"
                + address.getCity() + "\n" + address.getState()
                + " - " + address.getPinCode();
    }

}