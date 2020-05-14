package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.InventoryAddUpdateRequest;
import com.farmerzharvest.ecom.dto.InventoryResponse;
import com.farmerzharvest.ecom.dto.InventoryResponse.InventoryItem;
import com.farmerzharvest.ecom.model.product.Inventory;
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
        return InventoryItem.builder()
                .inventoryId(inventory.getId())
                .productId(inventory.getProduct().getId())
                .productName(inventory.getProduct().getName())
                .imageUrl(inventory.getProduct().getImageUrl())
                .productCategory(inventory.getProduct().getCategory().getCategoryName())
                .type(inventory.getType())
                .totalAvailableUnits(inventory.getTotalQuantity())
                .build();
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
