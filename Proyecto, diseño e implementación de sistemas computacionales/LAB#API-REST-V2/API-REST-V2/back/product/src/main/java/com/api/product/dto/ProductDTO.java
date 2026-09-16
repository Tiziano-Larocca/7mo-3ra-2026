package com.api.product.dto;

import jakarta.validation.constraints.*;

public record ProductDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name, 
        
        @NotNull(message = "Price must be higher than 0")
        @Positive
        float price, 
        
        @Min(0)
        @Positive
        int quantity) {

}
