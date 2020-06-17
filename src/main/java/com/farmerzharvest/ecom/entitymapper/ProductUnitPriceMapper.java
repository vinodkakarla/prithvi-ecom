package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.dto.ProductUnitPriceAddUpdateRequest;
import com.farmerzharvest.ecom.model.product.Product;
import com.farmerzharvest.ecom.model.product.ProductUnitPrice;
import com.farmerzharvest.ecom.model.product.ProductUnits;

import java.time.LocalDateTime;
import java.util.function.Function;

public class ProductUnitPriceMapper {

    public class ResponseMapper implements Function<ProductUnitPrice, ProductUnitPriceAddUpdateRequest> {

        public ProductUnitPriceAddUpdateRequest apply(ProductUnitPrice unitPrice) {
            return ProductUnitPriceAddUpdateRequest.builder()
                    .isActive(unitPrice.isActive())
                    .price(unitPrice.getUnitPrice())
                    .productUnitPriceId(unitPrice.getId())
                    .productId(unitPrice.getProduct().getId())
                    .productUnitId(unitPrice.getProductUnit().getId())
                    .build();
        }
    }

    public class RequestMapper implements Function<ProductUnitPriceAddUpdateRequest, ProductUnitPrice> {

        @Override
        public ProductUnitPrice apply(ProductUnitPriceAddUpdateRequest request) {
            ProductUnitPrice unitPrice = new ProductUnitPrice();

            unitPrice.setUpdatedAt(LocalDateTime.now());
            unitPrice.setActive(request.getIsActive());
            unitPrice.setUnitPrice(request.getPrice());

            Product product = new Product();
            product.setId(request.getProductId());
            unitPrice.setProduct(product);

            ProductUnits unit = new ProductUnits();
            unit.setId(request.getProductUnitId());
            unitPrice.setProductUnit(unit);

            return unitPrice;
        }
    }
}
