package com.InventoryApplication.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.InventoryApplication.category.GlobalExceptionHandler;

import jakarta.validation.Valid;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProd")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id)
            throws GlobalExceptionHandler {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/addProd")
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductRequest request)
            throws GlobalExceptionHandler {
        ProductResponseDto responseDto = productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/productName/{id}")
    public String getProductName(@PathVariable Long id) throws Exception {
    return productService.getProductName(id);
    }
}