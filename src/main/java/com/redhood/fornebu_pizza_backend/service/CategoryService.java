package com.redhood.fornebu_pizza_backend.service;

import com.redhood.fornebu_pizza_backend.entity.Category;
import com.redhood.fornebu_pizza_backend.repository.CategoryRepository;
import com.redhood.fornebu_pizza_backend.resources.CategoryResource;
import com.redhood.fornebu_pizza_backend.resources.CategoryNameOnlyResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResource> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> new CategoryResource(
                        category.getId(),
                        category.getName(),
                        category.getProducts()
                ))
                .collect(Collectors.toList());

    }

    public List<CategoryNameOnlyResource> getAllCategoryNamesAndIds() {
        // Fetch all categories from the repository
        List<Category> categories = categoryRepository.findAll();

        // Map Category entities to CategoryResources
        List<CategoryNameOnlyResource> categoryResources = categories.stream()
                .map(category -> new CategoryNameOnlyResource(category.getId(), category.getName()))
                .collect(Collectors.toList());

        return categoryResources;
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
