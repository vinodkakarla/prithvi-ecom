package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.ProductUnitDTO;
import com.farmerzharvest.ecom.model.product.ProductUnits;

public class ProductUnitsMapper {
    public ProductUnits mapProductDtoToModel(ProductUnitDTO unitDTO) {
        ProductUnits unitModel = new ProductUnits();
        unitModel.setUnitQuantity(unitDTO.getUnitQuantity());
        unitModel.setUnitType(unitDTO.getUnitType());
        return unitModel;
    }

    public ProductUnitDTO mapProductModelToDTO(ProductUnits unit) {
        return ProductUnitDTO.builder()
                .productUnitId(unit.getId())
                .unitQuantity(unit.getUnitQuantity())
                .unitType(unit.getUnitType())
                .build();
    }
}
