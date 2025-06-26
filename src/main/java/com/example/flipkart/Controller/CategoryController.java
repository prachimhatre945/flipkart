package com.example.flipkart.Controller;

import com.example.flipkart.Entity.Category;
import com.example.flipkart.Service.CatrgoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CatrgoryService catrgoryService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Category>> getAllCategories(){
        return catrgoryService.getAllCategories();
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return catrgoryService.addCategory(category);
    }
}
