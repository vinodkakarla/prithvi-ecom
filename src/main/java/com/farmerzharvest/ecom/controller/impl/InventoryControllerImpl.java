package com.farmerzharvest.ecom.controller.impl;

import com.farmerzharvest.ecom.controller.InventoryController;
import com.farmerzharvest.ecom.dto.InventoryResponse;
import com.farmerzharvest.ecom.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "inventory", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class InventoryControllerImpl implements InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @Override
    public InventoryResponse getInventory(@RequestParam(required = false) String categoryName) {
        log.debug("Received request to get all inventory by category: {}", categoryName);
        return inventoryService.getInventory(categoryName);
    }

    @GetMapping("/by-category-id")
    @Override
    public InventoryResponse getInventoryByCategoryId(@RequestParam(required = true) Long categoryId) {
        log.debug("Received request to get all inventory by category id: {}", categoryId);
        return inventoryService.getInventoryByCategoryId(categoryId);
    }

    @GetMapping("/by-product-id")
    @Override
    public InventoryResponse getInventoryByProductId(@RequestParam(required = true) Long productId) {
        log.debug("Received request to get all inventory by product id: {}", productId);
        return inventoryService.getInventoryByProductId(productId);
    }

}

