package com.api.product.controller;

import jakarta.persistence.EntityNotFoundException;
import com.api.product.entity.Product;
import com.api.product.service.ProductService;
import com.api.product.dto.ProductRequestDTO;
import com.api.product.dto.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin("*")
public class ProductController {

    //inject dependency
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO created = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
//    @PostMapping("/products")
//    //method to save product
//    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
//        System.out.println(productDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
//    }

    @GetMapping("/products")
    //method to get all products
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
        if (product == null) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        return ResponseEntity.ok(product);
    }
//    @GetMapping("/products/{id}")
//    //method to get product by id
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        return ResponseEntity.ok(productService.getProductById(id));
//    }

    @PutMapping("/products/{id}")
    //method to update product
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO dto) {
        System.out.println(id);
        System.out.println(dto);
        ProductResponseDTO existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/products/{id}")
    //method to delete product
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}