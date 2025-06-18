package com.example.flipkart.Service;

import com.example.flipkart.Constants.AppConstants;
import com.example.flipkart.Entity.Category;
import com.example.flipkart.Entity.Product;
import com.example.flipkart.ExceptionHandeling.CustomException;
import com.example.flipkart.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    public ResponseEntity<Product> createProduct(Product product){
        logger.debug("inside createProduct.... product created : {}", product);
        return ResponseEntity.status(201).body(productRepository.save(product));
    }

    public ResponseEntity<List<Product>> getAllProducts(){
        logger.info("inside getAllProducts" +productRepository.findAll() );
        return ResponseEntity.status(200).body(productRepository.findAll());
    }

    public ResponseEntity<Optional<Product>> findProductById(Long id){
        logger.info("inside findProductById with id : " +id);
        return ResponseEntity.status(200).body(productRepository.findById(id));
    }

    public ResponseEntity<Optional<Product>> getProductById (Long id){
        Optional<Product> isFound = productRepository.findById(id);
        if(isFound.isPresent()) {
            return ResponseEntity.status(200).body(productRepository.findById(id));
        }
        else
        {
            throw new CustomException(AppConstants.PRODUCT_NOT_FOUND);
        }
    }

    public ResponseEntity<Optional<Product>> updateProduct(Long id, Product product) {
        Optional<Product> productFound = productRepository.findById(id);
        if(productFound.isPresent()){
            Product existingProduct = productFound.get();
            existingProduct.setBrand(product.getBrand());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            productRepository.save(existingProduct);
        }
        else
            throw new CustomException(AppConstants.PRODUCT_NOT_FOUND);
        return  ResponseEntity.status(200).body(productRepository.findById(product.getId()));
    }

    public ResponseEntity<?> deleteProduct(Long id) throws CustomException {
        Optional<Product> isFound = productRepository.findById(id);
        if(isFound.isPresent()){
            return ResponseEntity.status(200).body(AppConstants.PRODUCT_DELETED_SUCCESSFULLY);
        }
        else {
            throw new CustomException(AppConstants.PRODUCT_NOT_FOUND);
        }
    }

    public ResponseEntity<List<Product>> getProductsByCategory(Category category){
        List<Product> isPresent = productRepository.findByCategory(category);
        if(isPresent.size() > 0){
            return ResponseEntity.status(200).body(isPresent);
        }
        else{
            throw new CustomException(AppConstants.PRODUCT_NOT_FOUND);
        }
    }
    public ResponseEntity<List<Product>> getProductsByCategoryAndBrand(Category category , String  brand) {
        List<Product> isPresent = productRepository.findByCategoryAndBrand(category , brand);
        if(isPresent.size() > 0){
            return ResponseEntity.status(200).body(isPresent);
        }
        else{
            throw new CustomException(AppConstants.PRODUCT_NOT_FOUND);
        }
    }

    public ResponseEntity<List<Product>> havingStockGreaterThan5 (){
        return ResponseEntity.status(200).body(productRepository.havingStockGreaterThan5());
    }

    public ResponseEntity<List<Product>> filterByPrice () {
    return ResponseEntity.status(200).body(productRepository.filterByPrice());
    }
    }
