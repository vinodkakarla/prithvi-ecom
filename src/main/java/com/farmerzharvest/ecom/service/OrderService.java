package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.OrderRequest;
import com.farmerzharvest.ecom.dto.OrderResponse;
import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.model.order.Orders;

import java.util.Collection;
import java.util.List;

public interface OrderService {
  List<ProductResponse> getProducts();

  List<ProductResponse> getProductsByCategrory(int categoryId);

  ProductResponse getProduct(Long productId);

  OrderResponse getOrderByOrderId(String orderId);

  Collection<OrderResponse> getOrdersByUser(String username);

  Collection<OrderResponse> getAllOrders();

  Orders saveOrder(String username, OrderRequest orderRequest);
}
