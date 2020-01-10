package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductController {

  List<ProductResponse> getProducts();

  ProductResponse getProduct(Long productId);

  @GetMapping("/{category}")
  List<ProductResponse> getProduct(@PathVariable String category);
}
