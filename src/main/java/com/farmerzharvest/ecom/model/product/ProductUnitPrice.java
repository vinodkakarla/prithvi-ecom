package com.farmerzharvest.ecom.model.product;

import com.farmerzharvest.ecom.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProductUnitPrice extends BaseEntity {

    @Id
    private Long id;
    private float unitPrice;
    @Column(nullable = false,columnDefinition = "boolean default true")
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "product_unit_id")
    private ProductUnits productUnit;

}