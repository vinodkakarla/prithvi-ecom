package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.ProductResponse;
import com.farmerzharvest.ecom.model.product.Product;
import com.farmerzharvest.ecom.model.product.ProductUnitPrice;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.function.Function;

public class ProductResponseMapper implements Function<Product, ProductResponse> {

    public ProductResponse apply(Product product) {
        ProductResponse.ProductResponseBuilder builder = ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .category(product.getCategory().getCategoryName())
                .isActive(product.isActive());

        Collection<ProductResponse.UnitDetail> unitDetails = Lists.newArrayList();

        if (product.getProductUnits() != null) {
            for (ProductUnitPrice productUnit : product.getProductUnits()) {
                ProductResponse.UnitDetail unitDetail = ProductResponse.UnitDetail.builder()
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
