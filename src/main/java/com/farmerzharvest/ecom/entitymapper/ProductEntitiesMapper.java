package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.ProductAddUpdateRequest;
import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.model.product.Category;
import com.farmerzharvest.ecom.model.product.Inventory;
import com.farmerzharvest.ecom.model.product.Product;
import com.farmerzharvest.ecom.model.product.ProductUnitPrice;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.function.Function;

public class ProductEntitiesMapper {

    public class ProductResponseMapper implements Function<Product, ProductResponse> {

        public ProductResponse apply(Product product) {
            ProductResponse.ProductResponseBuilder builder = ProductResponse.builder()
                    .id(product.getId())
                    .description(product.getDescription())
                    .name(product.getName())
                    .imageUrl(product.getImageUrl())
                    .category(product.getCategory().getCategoryName())
                    .totalQuantity(product.getInventory() != null ? product.getInventory().getTotalQuantity() : 0)
                    .isActive(product.isActive());

            Collection<ProductResponse.UnitDetail> unitDetails = Lists.newArrayList();

            if (product.getProductUnits() != null) {
                for (ProductUnitPrice productUnit : product.getProductUnits()) {
                    ProductResponse.UnitDetail unitDetail = ProductResponse.UnitDetail.builder()
                            .id(productUnit.getId())
                            .unitQuantity(productUnit.getProductUnit().getUnitQuantity())
                            .unitType(productUnit.getProductUnit().getUnitType())
                            .pricePerUnit(productUnit.getUnitPrice())
                            .build();
                    unitDetails.add(unitDetail);
                }

                builder.unitDetails(unitDetails);
            }

            return builder.build();
        }
    }

    public class ProductAddUpdateRequestMapper implements Function<ProductAddUpdateRequest, Product> {

        @Override
        public Product apply(ProductAddUpdateRequest request) {
            Product product = new Product();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setImageUrl(request.getImageUrl());
            product.setActive(request.getIsActive());
            product.setUpdatedAt(LocalDateTime.now());

            Category category = new Category();
            category.setId(request.getCategoryId());
            product.setCategory(category);

            Inventory inventory = new Inventory();
            inventory.setId(request.getInventoryId());
            product.setInventory(inventory);

            return product;
        }
    }
}
