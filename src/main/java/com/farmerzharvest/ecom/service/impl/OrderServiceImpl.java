package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.OrderRequest;
import com.farmerzharvest.ecom.dto.OrderResponse;
import com.farmerzharvest.ecom.entitymapper.OrderResponseMapper;
import com.farmerzharvest.ecom.exception.ResourceNotFoundException;
import com.farmerzharvest.ecom.model.accounts.AccountAddress;
import com.farmerzharvest.ecom.model.accounts.User;
import com.farmerzharvest.ecom.model.order.OrderDetails;
import com.farmerzharvest.ecom.model.order.Orders;
import com.farmerzharvest.ecom.model.product.Product;
import com.farmerzharvest.ecom.model.product.ProductUnits;
import com.farmerzharvest.ecom.repository.*;
import com.farmerzharvest.ecom.service.OrderService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;
    private final ProductUnitsRepository unitsRepository;
    private final AccountAddressRepository addressRepository;
    private final UserRepository accountRepository;
    private OrderResponseMapper responseMapper;

    @PostConstruct
    public void setup() {
        responseMapper = new OrderResponseMapper();
    }


    @Override
    public OrderResponse getOrderByOrderId(long orderId) {
        return responseMapper.mapOrderToResponse(ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No order found with the given orderId")));
    }

    @Override
    public Collection<OrderResponse> getOrdersByUser(String username) {
        return toOrderResponse(ordersRepository.findAllByAccount_username(username));
    }

    @Override
    public Collection<OrderResponse> getAllOrders() {
        return toOrderResponse(ordersRepository.findAll());
    }

    @Override
    public OrderResponse updateStatus(long orderId, String status) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No order found with the given orderId"));
        order.setStatus(status);
        return responseMapper.mapOrderToResponse(order);
    }

    @Override
    public Orders saveOrder(String username, OrderRequest orderRequest) {
        User account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        Orders order = toOrders(orderRequest, account);
        return ordersRepository.save(order);
    }

    private Collection<OrderResponse> toOrderResponse(Collection<Orders> orders) {
        Collection<OrderResponse> orderResponses =
                orders.stream()
                    .sorted((x1, x2) -> (x1.getOrderDate()==null||x2.getOrderDate()==null) ? -1 : x2.getOrderDate().compareTo(x1.getOrderDate()))
                    .map(responseMapper::mapOrderToResponse).collect(Collectors.toList());
        return orderResponses;
    }

    private Orders toOrders(OrderRequest orderRequest, User account) {
        List<OrderDetails> orderDetails = Lists.newArrayList();

        Orders orders = new Orders();
        for (OrderRequest.OrderProductDetail opd : orderRequest.getOrderDetails()) {
            OrderDetails detail = toOrderDetails(opd, account);
            detail.setOrderId(orders);
            orderDetails.add(detail);
        }

        orders.setOrderDetails(orderDetails);
        AccountAddress address = addressRepository.findById(orderRequest.getAccountAddressId()).orElseThrow(
                () -> new ResourceNotFoundException("AccountAddress", "accountAddressId",
                        orderRequest.getAccountAddressId()));
        orders.setAccountAddress(address);
        orders.setPickUp(orderRequest.getPickUp());
        orders.setTotalAmount(orderRequest.getTotalAmount());
        orders.setCreatedAt(LocalDateTime.now());
        orders.setCreatedBy(account.getId());
        orders.setAccount(account);
        orders.setOrderDate(LocalDateTime.now());
        orders.setStatus(orderRequest.getStatus());
        orders.setSlot(orderRequest.getSlot());
        return orders;
    }

    private OrderDetails toOrderDetails(OrderRequest.OrderProductDetail opd, User account) {
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setPricePerUnit(opd.getPricePerUnit());
        orderDetail.setTotalUnitAmount(opd.getTotalUnitAmount());
        orderDetail.setUnitQuantity(opd.getProductQuantity());
        Product product = productRepository.findById(opd.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productId", opd.getProductId()));
        orderDetail.setProduct(product);
        ProductUnits unit = unitsRepository.findById(opd.getUnitId())
                .orElseThrow(() -> new ResourceNotFoundException("ProductUnits", "unitId", opd.getUnitId()));
        orderDetail.setUnit(unit);
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy(account.getId());
        return orderDetail;
    }

}
