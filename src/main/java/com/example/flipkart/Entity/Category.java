package com.example.flipkart.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category" , uniqueConstraints = @UniqueConstraint( columnNames = "category_name"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Category name should not be empty")
    @Column(name = "category_name")
    private String name;
    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> productList ;

    public Category() {
    }

    public Category(Long id, String name, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Category name should not be empty") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Category name should not be empty") String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
