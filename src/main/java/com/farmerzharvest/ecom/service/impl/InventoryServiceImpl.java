package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.InventoryAddUpdateRequest;
import com.farmerzharvest.ecom.dto.InventoryResponse;
import com.farmerzharvest.ecom.entitymapper.InventoryEntitiesMapper;
import com.farmerzharvest.ecom.model.product.Inventory;
import com.farmerzharvest.ecom.repository.InventoryRepository;
import com.farmerzharvest.ecom.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    private InventoryEntitiesMapper inventoryEntitiesMapper;

    @PostConstruct
    public void setup() {
        inventoryEntitiesMapper = new InventoryEntitiesMapper();
    }

    @Override
    public InventoryResponse getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventoryEntitiesMapper.mapToInventoryResponse(inventories);
    }

    @Override
    public InventoryResponse getInventory(String categoryName) {
        if (StringUtils.isEmpty(categoryName)) {
            return getAllInventory();
        }
        List<Inventory> inventories = inventoryRepository.findAllByProduct_Category_categoryName(categoryName);
        return inventoryEntitiesMapper.mapToInventoryResponse(inventories);
    }

    @Override
    public InventoryResponse getInventoryByCategoryId(Long categoryId) {
        List<Inventory> inventories = inventoryRepository.findAllByProduct_Category_id(categoryId);
        return inventoryEntitiesMapper.mapToInventoryResponse(inventories);
    }

    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {
        List<Inventory> inventories = inventoryRepository.findAllByProduct_id(productId);
        return inventoryEntitiesMapper.mapToInventoryResponse(inventories);
    }

    @Override
    public InventoryResponse.InventoryItem addInventoryItem(InventoryAddUpdateRequest request) {
        Inventory inventory = inventoryEntitiesMapper.mapToInventoryModel(request);
        inventory.setCreatedAt(LocalDateTime.now());
        return inventoryEntitiesMapper.buildInventoryItem(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponse.InventoryItem updateInventoryItem(InventoryAddUpdateRequest request) {
        Inventory inventory = inventoryEntitiesMapper.mapToInventoryModel(request);
        inventory.setId(request.getInventoryId());
        return inventoryEntitiesMapper.buildInventoryItem(inventoryRepository.save(inventory));
    }

    @Override
    public void deleteInventoryItem(long inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }
}
