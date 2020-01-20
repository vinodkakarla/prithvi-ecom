package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.entitymapper.ProductResponseMapper;
import com.farmerzharvest.ecom.model.product.Product;
import com.farmerzharvest.ecom.repository.ProductRepository;
import com.farmerzharvest.ecom.service.OrderService;
import com.farmerzharvest.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final ProductRepository productRepository;
  private ProductResponseMapper mapperFunction;

  @PostConstruct
  public void setup() {
    mapperFunction = new ProductResponseMapper();
  }


  public List<ProductResponse> getProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream()
        .map(mapperFunction::apply)
        .collect(Collectors.toList());
  }

  @Override
  public List<ProductResponse> getProductsByCategrory(int categoryId) {
    List<Product> products = productRepository.findAllByCategory_id((long)categoryId);
    return products.stream()
            .map(mapperFunction::apply)
            .collect(Collectors.toList());
  }

  public ProductResponse getProduct(Long productId) {
    Optional<Product> product = productRepository.findById(productId);
    if (! product.isPresent()) {
      throw new RuntimeException("No product with the given identifier");
    }
    return  mapperFunction.apply(product.get());
  }

}
