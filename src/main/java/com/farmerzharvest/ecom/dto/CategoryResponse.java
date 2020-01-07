package com.farmerzharvest.ecom.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
  private List<CategoryItem> categoryItems;

  @Getter
  @Setter
  @Builder
  public static class CategoryItem {
    private Long categoryId;
    private String categoryName;
    private String description;
  }
}
