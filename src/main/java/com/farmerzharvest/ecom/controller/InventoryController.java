package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.InventoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface InventoryController {

    InventoryResponse getInventory(String categoryName);

    @GetMapping("/by-category-id")
    InventoryResponse getInventoryByCategoryId(@RequestParam(required = true) Long categoryId);

    @GetMapping("/by-product-id")
    InventoryResponse getInventoryByProductId(@RequestParam(required = true) Long productId);

}
