package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.ProductResponse.UnitDetail;
import com.farmerzharvest.ecom.dto.ProductUnitPriceAddUpdateRequest;

public interface ProductUnitPriceService {

    UnitDetail addProductUnitPrice(ProductUnitPriceAddUpdateRequest request);

    UnitDetail updateProductUnitPrice(ProductUnitPriceAddUpdateRequest request);

    void deleteProductUnitPrice(long productUnitPriceId);

}
