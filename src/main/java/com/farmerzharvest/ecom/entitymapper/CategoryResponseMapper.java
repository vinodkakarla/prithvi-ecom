package com.farmerzharvest.ecom.entitymapper;

import com.farmerzharvest.ecom.dto.CategoryResponse;
import com.farmerzharvest.ecom.dto.CategoryResponse.CategoryItem;
import com.farmerzharvest.ecom.model.product.Category;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResponseMapper {

  public CategoryResponse mapToCategoryResponse(List<Category> categoryList) {
    if (!CollectionUtils.isEmpty(categoryList)) {
      List<CategoryItem> items = categoryList.stream()
          .map(this::buildCategoryItem)
          .collect(Collectors.toList());
      return new CategoryResponse(items);
    }
    return new CategoryResponse();
  }

  private CategoryItem buildCategoryItem(Category category) {
    return CategoryItem.builder()
            .categoryId(category.getId())
            .categoryName(category.getCategoryName())
            .description(category.getDescription())
            .build();
  }
}
