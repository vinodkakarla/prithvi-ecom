package com.farmerzharvest.ecom.repository;

import com.farmerzharvest.ecom.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByAccount_id(long id);

    List<Orders> findAllByAccount_username(String username);

}
