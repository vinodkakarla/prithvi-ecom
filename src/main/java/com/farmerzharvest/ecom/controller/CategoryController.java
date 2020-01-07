package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.dto.CategoryResponse;
import com.farmerzharvest.ecom.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "category", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public CategoryResponse listCategories() {
        log.debug("Received request to get all inventory");
        return categoryService.listCategories();
    }
}
