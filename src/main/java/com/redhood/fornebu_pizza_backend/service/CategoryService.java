package com.redhood.fornebu_pizza_backend.service;

import com.redhood.fornebu_pizza_backend.entity.Category;
import com.redhood.fornebu_pizza_backend.repository.CategoryRepository;
import com.redhood.fornebu_pizza_backend.resources.CategoryResource;
import com.redhood.fornebu_pizza_backend.resources.CategoryBasicResource;
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

    public List<CategoryResource> getAllCategoriesHasProduct() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .filter(category -> !category.getProducts().isEmpty())
                .map(category -> new CategoryResource(
                        category.getId(),
                        category.getName(),
                        category.getProducts()
                ))
                .collect(Collectors.toList());

    }

    public List<CategoryBasicResource> getAllBasicCategoryHasProduct() {
        // Fetch all categories from the repository
        List<Category> categories = categoryRepository.findAll();

        // Map Category entities to CategoryResources
        List<CategoryBasicResource> categoryResources = categories.stream()
                .filter(category -> !category.getProducts().isEmpty())
                .map(category -> new CategoryBasicResource(category.getId(), category.getName(),category.getProducts().size()))
                .collect(Collectors.toList());

        return categoryResources;
    }

    public List<CategoryBasicResource> getAllBasicCategory() {
        // Fetch all categories from the repository
        List<Category> categories = categoryRepository.findAll();

        // Map Category entities to CategoryResources
        List<CategoryBasicResource> categoryResources = categories.stream()
                .map(category -> new CategoryBasicResource(category.getId(), category.getName(),category.getProducts().size()))
                .collect(Collectors.toList());

        return categoryResources;
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatedCategory.getName()); // Update the category name
            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
