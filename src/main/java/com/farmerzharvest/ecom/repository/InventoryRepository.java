package com.farmerzharvest.ecom.repository;

import com.farmerzharvest.ecom.model.product.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByProduct_Category_categoryName(String category);
}
