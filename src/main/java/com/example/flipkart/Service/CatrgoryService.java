package com.example.flipkart.Service;

import com.example.flipkart.Entity.Category;
import com.example.flipkart.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatrgoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CatrgoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Category> addCategory(Category category){
      return ResponseEntity.status(200).body(categoryRepository.save(category));
    }

    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.status(200).body(categoryRepository.findAll());
    }
}
