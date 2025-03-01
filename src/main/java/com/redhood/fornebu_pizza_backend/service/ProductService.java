package com.redhood.fornebu_pizza_backend.service;

import com.redhood.fornebu_pizza_backend.dto.ProductDTO;
import com.redhood.fornebu_pizza_backend.dto.ProductOptionDTO;
import com.redhood.fornebu_pizza_backend.entity.Category;
import com.redhood.fornebu_pizza_backend.entity.Product;
import com.redhood.fornebu_pizza_backend.entity.ProductExtraDressing;
import com.redhood.fornebu_pizza_backend.entity.ProductSelectOption;
import com.redhood.fornebu_pizza_backend.repository.ProductRepository;
import com.redhood.fornebu_pizza_backend.resources.ProductResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository,CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public List<ProductResource> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductResource::new)
                .collect(Collectors.toList());
    }
    public List<ProductResource> getAllProductsWithFilter(Long categoryId) {
        List<Product> products;

        if (categoryId == null) {
            // If categoryId is null, return all products
            products = productRepository.findAll();
        } else {
            // Otherwise, filter products by category_id
            products = productRepository.findByCategoryId(categoryId);
        }

        return products.stream()
                .map(ProductResource::new)
                .collect(Collectors.toList());
    }

    // ✅ Updated: Get product by ID as ProductResource
    public Optional<ProductResource> getProductById(Long id) {
        System.out.println("id");
        System.out.println(id);
        return productRepository.findById(id)
                .map(ProductResource::new);
    }

    public List<ProductResource> searchProducts(String keyword) {
        List<Product> products = productRepository.searchByKeyword(keyword);
        return products.stream()
                .map(ProductResource::new)
                .collect(Collectors.toList());
    }

    public Product saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        return createOrUpdateProduct(product, productDTO);
    }

    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return createOrUpdateProduct(product, productDTO);
    }

    private Product createOrUpdateProduct(Product product, ProductDTO productDTO) {
        Category category = categoryService.getCategoryById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAllerg(productDTO.getAllerg());
        product.setStock(productDTO.getStock());
        product.setAmount(productDTO.getAmount());
        product.setImgURl(productDTO.getImgURl());
        product.setCategory(category);

        // Set Select Options
        if (productDTO.getSelectOptions() != null) {
            List<ProductSelectOption> options = productDTO.getSelectOptions().stream()
                    .map(optionDTO -> {
                        ProductSelectOption option = new ProductSelectOption();
                        option.setName(optionDTO.getName());
                        option.setPrize(optionDTO.getPrice());
                        option.setProduct(product);
                        return option;
                    })
                    .collect(Collectors.toList());
            product.setSelectOptions(options);
        }

        // Set Deselect Options
        product.setDeselectOptions(productDTO.getDeselectOptions());

        // Set Extra Dressings
        if (productDTO.getExtraDressings() != null) {
            List<ProductExtraDressing> dressings = productDTO.getExtraDressings().stream()
                    .map(dressingDTO -> {
                        ProductExtraDressing dressing = new ProductExtraDressing();
                        dressing.setName(dressingDTO.getName());
                        dressing.setPrize(dressingDTO.getPrice());
                        dressing.setProduct(product);
                        return dressing;
                    })
                    .collect(Collectors.toList());
            product.setExtraDressing(dressings);
        }

        // Set Extras (linked products)
        if (productDTO.getExtras() != null && !productDTO.getExtras().isEmpty()) {
            List<Product> extraProducts = productRepository.findAllById(productDTO.getExtras());
            product.setExtras(extraProducts);
        }

        // Set Add Drinks (linked products)
        if (productDTO.getAddDrinks() != null && !productDTO.getAddDrinks().isEmpty()) {
            List<Product> addDrinkProducts = productRepository.findAllById(productDTO.getAddDrinks());
            product.setAddDrinks(addDrinkProducts);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
