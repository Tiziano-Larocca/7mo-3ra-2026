package com.api.product.service;

import com.api.product.dto.ProductDTO;
import com.api.product.entity.Product;
import java.util.List;

public interface ProductService {
    //create
    public Product createProduct(ProductDTO productDTO);

    //read
    List<Product> getAllProducts();
    public Product getProductById(Long productId);

    //update
    public Product updateProduct(Long productId, ProductDTO productDTO);

    //delete
    public Product deleteProductById(Long productId);

}
