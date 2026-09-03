package com.api.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
@Data //Lombok annotation to generate setter and getter
@Entity //JPA annotation to specify entity
@Table(name = "products") // JPA annotation to specify the table name for this entity
public class Product {
    //--------------------------------
    @Id //JPA annotation to specify primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA annotation to specify auto-increment
    private Long id; //this attribute is primary key and auto_increment
    //-----------------------------------
    private String name;
    private float price;
    private int quantity;
}
