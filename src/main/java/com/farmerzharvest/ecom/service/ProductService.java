package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProducts();

    List<ProductResponse> getProductsByCategoryId(int categoryId);

    List<ProductResponse> getProductsByCategoryName(String categoryName);

    ProductResponse getProduct(Long productId);
}
