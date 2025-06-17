package com.example.flipkart.Repository;

import com.example.flipkart.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    @Query("select p from Product p where p.stock > 5 ")
    public List<Product> havingStockGreaterThan5();

    @Query("select p from Product p where price > 1000 and price< 50000")
    public List<Product> filterByPrice();
    public List<Product> findByCategory(String category);
    public List<Product> findByCategoryAndBrand(String category , String brand);


}
