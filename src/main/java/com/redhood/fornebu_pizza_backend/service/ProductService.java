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

    // ✅ Updated: Get product by ID as ProductResource
    public Optional<ProductResource> getProductById(Long id) {
        System.out.println("id");
        System.out.println(id);
        return productRepository.findById(id)
                .map(ProductResource::new);
    }

    public Product saveProduct(ProductDTO productDTO) {
        Category category = categoryService.getCategoryById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAllerg(productDTO.isAllerg());
        product.setStock(productDTO.getStock());
        product.setAmount(productDTO.getAmount());
        product.setImgURl(productDTO.getImgURl());
        product.setCategory(category);

        //setSelectOptions
        if (productDTO.getSelectOptions() != null) {
            List<ProductSelectOption> options = new ArrayList<>();
            for (ProductOptionDTO selectOptionDTO : productDTO.getSelectOptions()) {
                ProductSelectOption option = new ProductSelectOption();
                option.setName(selectOptionDTO.getName());
                option.setPrize(selectOptionDTO.getPrice());
                option.setProduct(product);  // Link the option to the product
                options.add(option);
            }
            product.setSelectOptions(options);
        }

        product.setDeselectOptions(productDTO.getDeselectOptions());

        //setExtraDressing
        if (productDTO.getExtraDressings() != null) {
            List<ProductExtraDressing> options = new ArrayList<>();
            for (ProductOptionDTO extraDressingDTO : productDTO.getExtraDressings()) {
                ProductExtraDressing option = new ProductExtraDressing();
                option.setName(extraDressingDTO.getName());
                option.setPrize(extraDressingDTO.getPrice());
                option.setProduct(product);  // Link the option to the product
                options.add(option);
            }
            product.setExtraDressing(options);
        }

        if (productDTO.getExtras() != null && !productDTO.getExtras().isEmpty()) {
            List<Product> extraProducts = productRepository.findAllById(productDTO.getExtras());
            product.setExtras(extraProducts);
        }

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
