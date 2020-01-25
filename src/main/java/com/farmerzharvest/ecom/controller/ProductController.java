package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductController {

    List<ProductResponse> getProducts();

    ProductResponse getProduct(Long productId);

    @GetMapping("/by-category-id/{categoryId}")
    List<ProductResponse> getProduct(@PathVariable int categoryId);

    @GetMapping("/by-category-name/{categoryId}")
    List<ProductResponse> getProductByCategoryName(@PathVariable String categoryName);
}
