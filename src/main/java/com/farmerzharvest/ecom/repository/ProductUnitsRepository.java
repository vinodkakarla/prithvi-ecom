package com.farmerzharvest.ecom.repository;

import com.farmerzharvest.ecom.model.product.ProductUnits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductUnitsRepository extends JpaRepository<ProductUnits, Long> {

    @Query("SELECT DISTINCT a.unitType FROM ProductUnits a")
    List<String> findDistinctUnitType();
}
