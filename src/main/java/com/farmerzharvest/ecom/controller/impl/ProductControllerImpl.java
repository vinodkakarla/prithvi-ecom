package com.farmerzharvest.ecom.controller.impl;

import com.farmerzharvest.ecom.controller.ProductController;
import com.farmerzharvest.ecom.dto.ProductAddUpdateRequest;
import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/by-category-id/{categoryId}")
    @Override
    public List<ProductResponse> getProduct(@PathVariable int categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/by-category-name/{categoryName}")
    @Override
    public List<ProductResponse> getProductByCategoryName(@PathVariable String categoryName) {
        return productService.getProductsByCategoryName(categoryName);
    }

    @GetMapping("/search-by-name/{prodOrCatName}")
    @Override
    public List<ProductResponse> searchByProdOrCatName(@PathVariable String prodOrCatName) {
        return productService.searchProductsByCatNameOrProdName(prodOrCatName);
    }

    @Override
    public ProductResponse addProduct(ProductAddUpdateRequest request) {
        return productService.addProduct(request);
    }

    @Override
    public ProductResponse updateProduct(ProductAddUpdateRequest request) {
        return productService.updateProduct(request);
    }

}