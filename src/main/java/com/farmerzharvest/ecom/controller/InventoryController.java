package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.InventoryAddUpdateRequest;
import com.farmerzharvest.ecom.dto.InventoryResponse;
import org.springframework.web.bind.annotation.*;

public interface InventoryController {

    InventoryResponse getInventory(String categoryName);

    @GetMapping("/by-category-id")
    InventoryResponse getInventoryByCategoryId(@RequestParam(required = true) Long categoryId);

    @GetMapping("/by-product-id")
    InventoryResponse getInventoryByProductId(@RequestParam(required = true) Long productId);

    @PostMapping("/add-inventory-item")
    InventoryResponse.InventoryItem addInventoryItem(@RequestBody(required = true) InventoryAddUpdateRequest request);

    @PutMapping("/update-inventory-item")
    InventoryResponse.InventoryItem updateInventoryItem(
            @RequestBody(required = true) InventoryAddUpdateRequest request);

    @DeleteMapping("/inventory-item/{inventoryId}")
    void deleteInventoryItem(@PathVariable(value = "inventoryId") long inventoryId);

}
