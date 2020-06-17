package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.dto.ProductResponse.UnitDetail;
import com.farmerzharvest.ecom.dto.ProductUnitPriceAddUpdateRequest;
import com.farmerzharvest.ecom.service.ProductService;
import com.farmerzharvest.ecom.service.ProductUnitPriceService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "product-unit-price", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductUnitPriceController {

    private final ProductUnitPriceService unitPriceService;
    private final ProductService productService;

    @GetMapping("/get-unit-prices-by-product")
    List<UnitDetail> getProductUnitPricesByProductId(long productId) {
        ProductResponse product = productService.getProduct(productId);
        if (product == null) {
            return Lists.newArrayList();
        } else {
            return product.getUnitDetails().stream().collect(Collectors.toList());
        }
    }

    @PostMapping("/add-unit-price")
    ProductUnitPriceAddUpdateRequest addProductUnitPrice(ProductUnitPriceAddUpdateRequest request) {
        return unitPriceService.addProductUnitPrice(request);
    }

    @PostMapping("/add-update-unit-prices")
    List<ProductUnitPriceAddUpdateRequest> addOrUpdateProductUnitPrices(List<ProductUnitPriceAddUpdateRequest> request) {
        return unitPriceService.addOrUpdateProductUnitPrices(request);
    }

    @PutMapping("/update-unit-price")
    ProductUnitPriceAddUpdateRequest updateProductUnitPrice(ProductUnitPriceAddUpdateRequest request) {
        return unitPriceService.updateProductUnitPrice(request);
    }

    @DeleteMapping("/delete-unit-price")
    void deleteProductUnitPrice(long productUnitPriceId) {
        unitPriceService.deleteProductUnitPrice(productUnitPriceId);
    }

}
