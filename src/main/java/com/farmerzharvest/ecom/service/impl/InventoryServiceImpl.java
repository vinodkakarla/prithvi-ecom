package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.InventoryResponse;
import com.farmerzharvest.ecom.entitymapper.InventoryResponseMapper;
import com.farmerzharvest.ecom.model.product.Inventory;
import com.farmerzharvest.ecom.repository.InventoryRepository;
import com.farmerzharvest.ecom.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    private InventoryResponseMapper inventoryResponseMapper;

    @PostConstruct
    public void setup() {
        inventoryResponseMapper = new InventoryResponseMapper();
    }

    @Override
    public InventoryResponse getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventoryResponseMapper.mapToProductResponse(inventories);
    }

    @Override
    public InventoryResponse getInventory(String categoryName) {
        if (StringUtils.isEmpty(categoryName)) {
            return getAllInventory();
        }
        List<Inventory> inventories = inventoryRepository.findAllByProduct_Category_categoryName(categoryName);
        return inventoryResponseMapper.mapToProductResponse(inventories);
    }

    @Override
    public InventoryResponse getInventoryByCategoryId(Long categoryId) {
        List<Inventory> inventories = inventoryRepository.findAllByProduct_Category_id(categoryId);
        return inventoryResponseMapper.mapToProductResponse(inventories);
    }

    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {
        List<Inventory> inventories = inventoryRepository.findAllByProduct_id(productId);
        return inventoryResponseMapper.mapToProductResponse(inventories);
    }
}
