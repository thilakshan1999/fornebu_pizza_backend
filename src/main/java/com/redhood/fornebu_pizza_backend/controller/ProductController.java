package com.redhood.fornebu_pizza_backend.controller;

import com.redhood.fornebu_pizza_backend.dto.ProductDTO;
import com.redhood.fornebu_pizza_backend.entity.Product;
import com.redhood.fornebu_pizza_backend.resources.ProductResource;
import com.redhood.fornebu_pizza_backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product")
public class ProductController {
    private  final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResource> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductResource> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public List<ProductResource> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResource>> getAllProducts(@RequestParam(value = "categoryId", required = false) Long categoryId) {
        // Call service method to get products filtered by categoryId
        List<ProductResource> products = productService.getAllProductsWithFilter(categoryId);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        System.out.println("productDTO");
        System.out.println(productDTO);
        System.out.println(productDTO.getDeselectOptions());
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
