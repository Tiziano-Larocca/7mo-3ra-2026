package com.api.product.service;

import com.api.product.dto.CategoryRequestDTO;
import com.api.product.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO dto);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO getCategoryById(Long categoryId);

    CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO dto);

    CategoryResponseDTO deleteCategoryById(Long categoryId);
}

