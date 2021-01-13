package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.OrderRequest;
import com.farmerzharvest.ecom.dto.OrderResponse;
import com.farmerzharvest.ecom.model.order.Orders;

import java.util.Collection;

public interface OrderService {

    OrderResponse getOrderByOrderId(long orderId);

    Collection<OrderResponse> getOrdersByUser(String username);

    Collection<OrderResponse> getAllOrders();

    OrderResponse updateStatus(long orderId, String status);

    Orders saveOrder(String username, OrderRequest orderRequest);
}
