package com.example.flipkart.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.message.Message;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "product" , uniqueConstraints = @UniqueConstraint(columnNames = {"name", "brand"}))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name should not be empty")
    private String name;
    @Size(min = 3 , max = 20 , message = "Name must be between 3 and 20 characters")
    private String brand;
    private double price;
    private int stock;
//    @NotNull(message = "Category should not be empty")
//    private String category;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_category"))
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, String brand, double price, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
