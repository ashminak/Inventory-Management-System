package com.InventoryApplication.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.InventoryApplication.product.Product;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseDTO getCategoryByName(String name) throws GlobalExceptionHandler {
        Category category = categoryRepository.findByName(name).orElse(null);

        if (category == null) {
            throw new GlobalExceptionHandler("Category not found");
            
        }
        
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.id = category.getId();
        responseDTO.name = category.getName();
       

        return responseDTO;
    }

    public ResponseDTO createCategory(String name) throws GlobalExceptionHandler {
        Category category = new Category(name);
        if (categoryRepository.existsByName(name)) {
            throw new GlobalExceptionHandler("Category with the same name already exists");
        }
        categoryRepository.save(category);
        return getCategoryByName(name);
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
                
    }

    public void deleteCategory(String name) throws GlobalExceptionHandler {
        Category category = categoryRepository.findByName(name).orElse(null);
        if (category == null) {
            throw new GlobalExceptionHandler("Category not found");
        }
        categoryRepository.delete(category);
    }


}