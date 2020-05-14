package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.ProductUnitDTO;
import com.farmerzharvest.ecom.service.ProductUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "product-unit", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductUnitsController {

    private final ProductUnitService unitService;

    @GetMapping("/get-product-units")
    List<ProductUnitDTO> getProductUnits() {
        return unitService.getProductUnits();
    }

    @GetMapping("/product-unit-by-id/{id}")
    ProductUnitDTO getProductUnitById(@PathVariable(value = "id") long id) {
        return unitService.getProductUnitById(id);
    }

    @GetMapping("/get-available-unit-types")
    List<String> getValidProductUnitTypes() {
        return unitService.getValidProductUnitTypes();
    }

    @PostMapping("/add-product-unit")
    ProductUnitDTO addProductUnit(@RequestBody ProductUnitDTO productUnit) {
        return unitService.addProductUnit(productUnit);
    }

    @PutMapping("/add-product-unit")
    ProductUnitDTO updateProductUnit(@RequestBody ProductUnitDTO productUnit) {
        return unitService.updateProductUnit(productUnit);
    }

    @DeleteMapping("/delete-product-unit/{productUnitId}")
    void deleteProductUnit(@PathVariable(value = "productUnitId") Long productUnitId) {
        unitService.deleteProductUnit(productUnitId);
    }

}
