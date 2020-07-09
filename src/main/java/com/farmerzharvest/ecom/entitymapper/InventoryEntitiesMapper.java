package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.InventoryAddUpdateRequest;
import com.farmerzharvest.ecom.dto.InventoryResponse;
import com.farmerzharvest.ecom.dto.InventoryResponse.InventoryItem;
import com.farmerzharvest.ecom.model.product.Inventory;
import com.farmerzharvest.ecom.model.product.Product;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryEntitiesMapper {

    public InventoryResponse mapToInventoryResponse(List<Inventory> inventoryList) {
        if (!CollectionUtils.isEmpty(inventoryList)) {
            List<InventoryItem> items = inventoryList.stream()
                    .map(this::buildInventoryItem)
                    .collect(Collectors.toList());
            return new InventoryResponse(items);
        }
        return new InventoryResponse();
    }

    public InventoryItem buildInventoryItem(Inventory inventory) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setInventoryId(inventory.getId());
        Product product = inventory.getProduct();
        if(product!=null) {
            inventoryItem.setProductId(product.getId());
            inventoryItem.setProductName(product.getName());
            inventoryItem.setImageUrl(product.getImageUrl());
            if(product.getCategory()!=null){
                inventoryItem.setProductCategory(product.getCategory().getCategoryName());
            }
        }
        inventoryItem.setType(inventory.getType());
        inventoryItem.setTotalAvailableUnits(inventory.getTotalQuantity());

        return inventoryItem;
    }

    public Inventory mapToInventoryModel(InventoryAddUpdateRequest request) {
        Inventory inventory = new Inventory();
        inventory.setActive(request.getIsActive());
        inventory.setTotalQuantity(request.getTotalAvailableUnits());
        inventory.setType(request.getType());
        inventory.setUpdatedAt(LocalDateTime.now());
        return inventory;
    }
}
