package com.farmerzharvest.ecom.repository;

import com.farmerzharvest.ecom.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
