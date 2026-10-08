package com.api.product.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(
        @NotNull(message = "Name must contain characters")
        String name) {}

