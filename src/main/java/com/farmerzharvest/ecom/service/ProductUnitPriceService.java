package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.ProductUnitPriceAddUpdateRequest;

import java.util.List;

public interface ProductUnitPriceService {

    ProductUnitPriceAddUpdateRequest addProductUnitPrice(ProductUnitPriceAddUpdateRequest request);

    List<ProductUnitPriceAddUpdateRequest> addOrUpdateProductUnitPrices(List<ProductUnitPriceAddUpdateRequest> request);

    ProductUnitPriceAddUpdateRequest updateProductUnitPrice(ProductUnitPriceAddUpdateRequest request);

    void deleteProductUnitPrice(long productUnitPriceId);

}
