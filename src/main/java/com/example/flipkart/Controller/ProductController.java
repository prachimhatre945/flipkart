package com.example.flipkart.Controller;

import com.example.flipkart.Entity.Product;
import com.example.flipkart.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
    public String TestValueFromPropertiesFile(){
        return appName;
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product){
        return productService.createProduct(product).getBody();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById (@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Product>> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) throws Exception {
        return ResponseEntity.status(200).body(productService.updateProduct(id,product).getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(Long id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<Product>> getProductsByCategory( @RequestParam String category){
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/by-category&brand")
    public ResponseEntity<List<Product>> getProductsByCategoryAndBrand( @RequestParam String category , @RequestParam String brand){
        return productService.getProductsByCategoryAndBrand(category , brand);
    }

    @GetMapping("/stock")
    public ResponseEntity<List<Product>> havingStockGreaterThan5(){
        return productService.havingStockGreaterThan5();
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> filterByPrice(Double price){
    return productService.filterByPrice();
    }
}
