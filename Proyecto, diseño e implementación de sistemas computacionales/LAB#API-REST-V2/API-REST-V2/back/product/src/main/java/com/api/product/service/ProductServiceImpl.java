package com.api.product.service;

import jakarta.persistence.EntityNotFoundException;
import com.api.product.dto.ProductDTO;
import com.api.product.entity.Product;
import com.api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setQuantity(productDTO.quantity());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    @Override
    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDTO.name());
            existingProduct.setPrice(productDTO.price());
            existingProduct.setQuantity(productDTO.quantity());
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

//    @Override
//    public List<ProductDTO> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public ProductDTO getProductById(Long productId) {
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
//    }
//
//    @Override
//    public ProductDTO updateProduct(Long productId, ProductDTO product) {
//        Optional<ProductDTO> optionalProduct = productRepository.findById(productId);
//        if (optionalProduct.isPresent()) {
//            ProductDTO existingProduct = optionalProduct.get();
//            existingProduct.setName(product.getName());
//            existingProduct.setPrice(product.getPrice());
//            existingProduct.setQuantity(product.getQuantity());
//            return productRepository.save(existingProduct);
//        }
//        return null;
////        Product existingProduct = productRepository.findById(productId)
////                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
////        //update field
////        existingProduct.setName(product.getName());
////        existingProduct.setPrice(product.getPrice());
////        existingProduct.setQuantity(product.getQuantity());
////        System.out.println(product);
////        return productRepository.save(existingProduct);
////
//}
//
//    @Override
//    public ProductDTO deleteProductById(Long productId) {
//        Optional<ProductDTO> optionalProduct = productRepository.findById(productId);
//        if (optionalProduct.isPresent()) {
//            productRepository.deleteById(productId);
//            return optionalProduct.get();
//        }
//        return null;
//    }
////        return productRepository.findById(productId)
////                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
////    }

}
