package com.api.product.service;

import com.api.product.dto.CategoryRequestDTO;
import com.api.product.dto.CategoryResponseDTO;
import com.api.product.entity.Category;
import com.api.product.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {

        // 1. crear la categoría
        Category category = new Category();
        category.setName(dto.name());

        // 2. guardar
        Category saved = categoryRepository.save(category);

        // 3. armar el DTO de respuesta
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());

        return response;
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> {
                    CategoryResponseDTO response = new CategoryResponseDTO();
                    response.setId(category.getId());
                    response.setName(category.getName());

                    return response;
                })
                .toList();
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .map(category -> {
                    CategoryResponseDTO response = new CategoryResponseDTO();
                    response.setId(category.getId());
                    response.setName(category.getName());

                    return response;
                })
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Category not found with id: " + categoryId
                        )
                );
    }

    @Override
    public CategoryResponseDTO updateCategory(
            Long categoryId,
            CategoryRequestDTO dto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Category not found with id: " + categoryId
                        )
                );

        category.setName(dto.name());

        Category updated = categoryRepository.save(category);

        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(updated.getId());
        response.setName(updated.getName());

        return response;
    }

    @Override
    public CategoryResponseDTO deleteCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Category not found with id: " + categoryId
                        )
                );

        categoryRepository.delete(category);

        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }
}
