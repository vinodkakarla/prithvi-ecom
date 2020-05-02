package com.farmerzharvest.ecom.model.product;

import com.farmerzharvest.ecom.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Getter
@Setter
public class Inventory extends BaseEntity {

    @Id
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Product product;

    private Float totalQuantity;

    private String type;

    private boolean isActive;
}
