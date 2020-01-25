package com.farmerzharvest.ecom.model.product;

import com.farmerzharvest.ecom.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    @Id
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean isActive;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToMany
    @JoinColumn(name = "product_unit_price_id")
    private Collection<ProductUnitPrice> productUnits;
}

