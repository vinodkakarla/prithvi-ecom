package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.OrderRequest;
import com.farmerzharvest.ecom.dto.OrderResponse;
import com.farmerzharvest.ecom.model.order.Orders;
import com.farmerzharvest.ecom.repository.OrdersRepository;
import com.farmerzharvest.ecom.security.CurrentUser;
import com.farmerzharvest.ecom.security.UserPrincipal;
import com.farmerzharvest.ecom.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/orders")
@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public OrderResponse getOrderInfo(@PathVariable(value = "orderId") long orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    @GetMapping("/my-orders")
    public Collection<OrderResponse> getMyOrders(@CurrentUser UserPrincipal currentUser) {
        return orderService.getOrdersByUser(currentUser.getUsername());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-orders")
    public Collection<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("update-status/{orderId}/{status}")
    public OrderResponse updateStatus(@PathVariable(value = "orderId") long orderId,
                                            @PathVariable(value = "status") String status) {
        return orderService.updateStatus(orderId, status);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderRequest orderRequest,
                                       @CurrentUser UserPrincipal currentUser) {
        Orders order = orderService.saveOrder(currentUser.getUsername(), orderRequest);

        if (order == null) {
            return new ResponseEntity<String>("Order creation failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<String>("Order created successfully", HttpStatus.OK);
        }
    }


}
