package com.farmerzharvest.ecom.repository;

import com.farmerzharvest.ecom.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_categoryName(String category);

}
