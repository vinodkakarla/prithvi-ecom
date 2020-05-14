package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.ProductUnitDTO;

import java.util.List;

public interface ProductUnitService {
    List<ProductUnitDTO> getProductUnits();

    ProductUnitDTO getProductUnitById(long id);

    List<String> getValidProductUnitTypes();

    ProductUnitDTO addProductUnit(ProductUnitDTO productUnit);

    ProductUnitDTO updateProductUnit(ProductUnitDTO productUnit);

    void deleteProductUnit(Long productUnitId);
}
