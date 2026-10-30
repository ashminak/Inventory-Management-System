package com.InventoryApplication.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.InventoryApplication.category.Category;
import com.InventoryApplication.category.CategoryRepository;
import com.InventoryApplication.category.GlobalExceptionHandler;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepository categoryRepository;
    private final ProductClient productClient;

    public ProductService(ProductRepo productRepo, CategoryRepository categoryRepository, ProductClient productClient) {
        this.productRepo = productRepo;
        this.categoryRepository = categoryRepository;
        this.productClient = productClient;
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public ProductResponseDto getProductById(Long id) throws GlobalExceptionHandler {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler("Product not found with id: " + id));
        return toDto(product);
    }

    public ProductResponseDto addProduct(ProductRequest request) throws GlobalExceptionHandler {

        Category category = categoryRepository.findByName(request.category)
                .orElseThrow(() -> new GlobalExceptionHandler("Category not found: " + request.category));

        Product product = new Product();
        product.setName(request.name);
        product.setQuantity(request.quantity);
        product.setDesc(request.desc);
        product.setPrice(request.price);
        product.setCategory(category);

        Product saved = productRepo.save(product);
        return toDto(saved);
    }

    // one place to convert Product -> ProductResponseDto
    private ProductResponseDto toDto(Product p) {
        return new ProductResponseDto(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getCategory().getId(),
                p.getCategory().getName(),
                p.getDesc(),
                p.getQuantity());
    }

       public String getProductName(Long id) throws Exception {
        return productClient.getProductName(id);
    }
  
}