package com.api.product.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
@Data //Lombok annotation to generate setter and getter
@Entity //JPA annotation to specify entity
@Table(name = "products") // JPA annotation to specify the table name for this entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private Integer quantity;

    // lado "muchos": muchos productos apuntan a una categoría
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // getters, setters, constructores
}
