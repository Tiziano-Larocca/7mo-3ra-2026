package com.api.product.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // lado "uno": una categoría tiene muchos productos
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    // getters, setters, constructores
}
