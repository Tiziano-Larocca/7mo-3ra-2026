package com.api.product.service;

import com.api.product.dto.ProductRequestDTO;
import com.api.product.dto.ProductResponseDTO;
import com.api.product.entity.Product;
import java.util.List;

public interface ProductService {
    //create
    public ProductResponseDTO createProduct(ProductRequestDTO dto);

    //read
    List<Product> getAllProducts();
    
    public ProductResponseDTO getProductById(Long productId);

    //update
    public Product updateProduct(Long productId, ProductRequestDTO dto);

    //delete
    public Product deleteProductById(Long productId);
}