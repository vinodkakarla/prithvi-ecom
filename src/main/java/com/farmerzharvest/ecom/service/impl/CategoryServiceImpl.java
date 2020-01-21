package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.dto.CategoryResponse;
import com.farmerzharvest.ecom.entitymapper.CategoryResponseMapper;
import com.farmerzharvest.ecom.model.product.Category;
import com.farmerzharvest.ecom.repository.CategoryRepository;
import com.farmerzharvest.ecom.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private CategoryResponseMapper categoryResponseMapper;

    @PostConstruct
    public void setup() {
        categoryResponseMapper = new CategoryResponseMapper();
    }

    @Override
    public CategoryResponse listCategories() {

        List<Category> categories = categoryRepository.findAll();
        return categoryResponseMapper.mapToCategoryResponse(categories);
    }
}
