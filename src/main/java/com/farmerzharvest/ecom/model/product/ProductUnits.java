package com.farmerzharvest.ecom.model.product;

import com.farmerzharvest.ecom.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ProductUnits extends BaseEntity {

    @Id
    private Long id;
    private String unitType;
    private Long unitQuantity;
}