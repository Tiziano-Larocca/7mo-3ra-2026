package com.api.product.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String categoryName;  // solo lo que el cliente necesita ver
    // getters y setters
}
