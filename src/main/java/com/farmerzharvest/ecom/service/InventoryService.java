package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.dto.InventoryAddUpdateRequest;
import com.farmerzharvest.ecom.dto.InventoryResponse;

public interface InventoryService {
    InventoryResponse getAllInventory();

    InventoryResponse getInventory(String categoryName);

    InventoryResponse getInventoryByCategoryId(Long categoryId);

    InventoryResponse getInventoryByProductId(Long productId);

    InventoryResponse.InventoryItem addInventoryItem(InventoryAddUpdateRequest request);

    InventoryResponse.InventoryItem updateInventoryItem(InventoryAddUpdateRequest request);

    void deleteInventoryItem(long inventoryId);
}
