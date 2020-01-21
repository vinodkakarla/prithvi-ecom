package com.farmerzharvest.ecom.model.product;

import com.farmerzharvest.ecom.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Inventory extends BaseEntity {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Long totalQuantity;

    private float unitPrice;

    private boolean isActive;
}
