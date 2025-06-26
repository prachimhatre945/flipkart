package com.example.flipkart.Controller;

import com.example.flipkart.Entity.Category;
import com.example.flipkart.Entity.Product;
import com.example.flipkart.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

//    test @value annotation to read values from app properties
    @Value("${application.name}")
    private String appName;

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String TestValueFromPropertiesFile(){
        return appName;
    }

    @PostMapping
    @CacheEvict(value = "products", key = "'allProducts'")
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct(@Valid @RequestBody Product product){
        System.out.println("Fetched from db");
        return productService.createProduct(product).getBody();
    }

    @GetMapping
    @Cacheable(value = "products" , key = "'allProducts'")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Product>> getAllProducts(){
        System.out.println("Fetched from db");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Optional<Product>> getProductById (@PathVariable Long id){
        return productService.getProductById(id);
    }

    @CachePut(value = "products" , key = "'allProducts'")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Optional<Product>> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) throws Exception {
        System.out.println("Fetched from db");
        return ResponseEntity.status(200).body(productService.updateProduct(id,product).getBody());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(Long id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/by-category")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Product>> getProductsByCategory( @RequestParam Category category){
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/by-category&brand")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Product>> getProductsByCategoryAndBrand( @RequestParam Category category , @RequestParam String brand){
        return productService.getProductsByCategoryAndBrand(category , brand);
    }

    @GetMapping("/stock")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Product>> havingStockGreaterThan5(){
        return productService.havingStockGreaterThan5();
    }

    @GetMapping("/price")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Product>> filterByPrice(Double price){
    return productService.filterByPrice();
    }
}
