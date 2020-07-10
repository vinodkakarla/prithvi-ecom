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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany
    @JoinColumn(name = "product_id")
    private Collection<ProductUnitPrice> productUnits;

    @Override
    public int hashCode() {
        return Long.toString(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Long id1 = this.id;
            Long id2 = ((Product) obj).id;
            if (id1 != null && id2 != null) {
                return id1.floatValue() == id2.floatValue();
            }
        }
        return false;
    }
}

