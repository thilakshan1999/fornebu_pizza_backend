package com.redhood.fornebu_pizza_backend.controller;

import com.redhood.fornebu_pizza_backend.entity.Category;
import com.redhood.fornebu_pizza_backend.resources.CategoryResource;
import com.redhood.fornebu_pizza_backend.resources.CategoryNameOnlyResource;
import com.redhood.fornebu_pizza_backend.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResource> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/simple")
    public List<CategoryNameOnlyResource> getAllCategoryNamesAndIds() {
        return categoryService.getAllCategoryNamesAndIds();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        System.out.println(category);
        logger.info("Category: {}", category);
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
