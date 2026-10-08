package com.api.product.service;

import jakarta.persistence.EntityNotFoundException;
import com.api.product.dto.ProductRequestDTO;
import com.api.product.dto.ProductResponseDTO;
import com.api.product.entity.Category;
import com.api.product.entity.Product;
import com.api.product.repository.ProductRepository;
import com.api.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        // 1. buscar la categoría por id
        Category category = categoryRepository.findById(dto.categoryId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // 2. crear el producto y relacionarlo
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());
        product.setCategory(category);

        // 3. guardar
        Product saved = productRepository.save(product);

        // 4. armar el DTO de respuesta
        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setPrice(saved.getPrice());
        response.setQuantity(saved.getQuantity());
        response.setCategoryName(saved.getCategory().getName());
        return response;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(product -> {
                    ProductResponseDTO response = new ProductResponseDTO();
                    response.setId(product.getId());
                    response.setName(product.getName());
                    response.setPrice(product.getPrice());
                    response.setQuantity(product.getQuantity());
                    response.setCategoryName(product.getCategory().getName());
                    return response;
                })
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }
 
    @Override
    public Product updateProduct(Long productId, ProductRequestDTO dto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(dto.name());
            existingProduct.setPrice(dto.price());
            existingProduct.setQuantity(dto.quantity());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public Product deleteProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(productId);
            return optionalProduct.get();
        }
        return null;
    }
}
