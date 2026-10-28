package com.InventoryApplication.category;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InventoryApplication.product.Product;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi {
    
    private final CategoryService categoryService;
    
    
    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
        @GetMapping("/{name}")
        public ResponseEntity<ResponseDTO> getCategoryByName(@PathVariable String name) throws GlobalExceptionHandler {
           return ResponseEntity.ok().body(categoryService.getCategoryByName(name));
          
        }
        @PostMapping("/create")
        public ResponseEntity<ResponseDTO> createCategory(@RequestBody CategoryRequest request) throws GlobalExceptionHandler {
            return ResponseEntity.ok().body(categoryService.createCategory(request.name()));
        }

        @GetMapping("/list")
        public ResponseEntity<List<Category>> listCategories() {
            return ResponseEntity.ok().body(categoryService.listCategories());
        }

        @DeleteMapping("/delete/{name}")
        public ResponseEntity<String> deleteCategory(@PathVariable String name) throws GlobalExceptionHandler {
            categoryService.deleteCategory(name);
            return ResponseEntity.ok().body("Category deleted successfully");
        }

}
