package com.farmerzharvest.ecom.model.product;

import com.farmerzharvest.ecom.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

  @Id
  private Long id;
  private String name;
  private String description;
  private String imageUrl;
  @OneToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "product_unit_id")
  private ProductUnits productUnits;
}

