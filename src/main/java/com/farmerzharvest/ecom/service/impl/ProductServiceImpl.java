package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.ProductAddUpdateRequest;
import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.entitymapper.ProductEntitiesMapper;
import com.farmerzharvest.ecom.model.product.Product;
import com.farmerzharvest.ecom.repository.ProductRepository;
import com.farmerzharvest.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private ProductEntitiesMapper.ProductResponseMapper respMapperFunction;
    private ProductEntitiesMapper.ProductAddUpdateRequestMapper reqMapperFunction;

    @PostConstruct
    public void setup() {
        ProductEntitiesMapper entitiesMapper = new ProductEntitiesMapper();
        respMapperFunction = entitiesMapper.new ProductResponseMapper();
        reqMapperFunction = entitiesMapper.new ProductAddUpdateRequestMapper();
    }


    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll(Sort.by("orderBy").ascending());
        return products.stream()
                .map(respMapperFunction::apply)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsByCategoryId(int categoryId) {
        List<Product> products = productRepository.findAllByCategory_id((long) categoryId, Sort.by("orderBy").ascending());
        return products.stream()
                .map(respMapperFunction::apply)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsByCategoryName(String categoryName) {
        List<Product> products = productRepository.findAllByCategory_categoryName(categoryName);
        return products.stream()
                .map(respMapperFunction::apply)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new RuntimeException("No product with the given identifier");
        }
        return respMapperFunction.apply(product.get());
    }

    @Override
    public List<ProductResponse> searchProductsByCatNameOrProdName(String searchString) {
        List<Product> productsBySearch =
                productRepository.findByCategory_categoryNameContainingIgnoreCase(searchString, Sort.by("orderBy").ascending());
        productsBySearch.addAll(productRepository.findByNameContainingIgnoreCase(searchString, Sort.by("orderBy").ascending()));
        return productsBySearch.stream().distinct()
                .map(respMapperFunction::apply)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse addProduct(ProductAddUpdateRequest request) {
        Product product = reqMapperFunction.apply(request);
        product.setCreatedAt(LocalDateTime.now());
        return respMapperFunction.apply(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(ProductAddUpdateRequest request) {
        Product product = reqMapperFunction.apply(request);
        product.setId(request.getProductId());
        return respMapperFunction.apply(productRepository.save(product));
    }

}
