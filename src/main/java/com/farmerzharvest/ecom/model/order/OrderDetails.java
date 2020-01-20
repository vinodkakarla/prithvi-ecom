package com.farmerzharvest.ecom.model.order;

import com.farmerzharvest.ecom.model.BaseEntity;
import com.farmerzharvest.ecom.model.product.Product;
import com.farmerzharvest.ecom.model.product.ProductUnits;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class OrderDetails extends BaseEntity {
    @Id
    private Long id;
    private float pricePerUnit;
    private short unitQuantity;
    private float totalUnitAmount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orderId;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private ProductUnits unit;
}
