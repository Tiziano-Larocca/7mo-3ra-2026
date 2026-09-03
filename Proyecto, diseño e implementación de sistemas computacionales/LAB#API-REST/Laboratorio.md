# API REST Spring Boot

Alumno: **Tiziano Larocca**

Profesor: **Vicente Cersosimo**

Curso: **7mo 3ra**

## Objetivo
Desarrollar una API REST funcional aplicando una arquitectura por capas. El proyecto deberá separar responsabilidades entre Controller, Service, Repository, DTO y Entity, utilizar JPA para la persistencia y
centralizar la configuración en application.yaml.

### Código ProductController.java (controlador)
```java
package com.api.product.controller;

import jakarta.persistence.EntityNotFoundException;
import com.api.product.entity.Product;
import com.api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ProductController {

    //inject dependency
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    //method to save product
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        System.out.println(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping("/products")
    //method to get all products
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    //method to get product by id
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/products/{id}")
    //method to update product
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        System.out.println(id);
        System.out.println(product);
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/products/{id}")
    //method to delete product
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
```

### Código Product.java (clase)
```java
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

```

### Código ProductRepository.java (repositorio)
```java
package com.api.product.repository;

import com.api.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}

```

### Código ProductService.java (interfaz)
```java
package com.api.product.service;

import com.api.product.entity.Product;
import java.util.List;

public interface ProductService {
    //create
    public Product createProduct(Product product);

    //read
    public List<Product> getAllProducts();
    public Product getProductById(Long productId);

    //update
    public Product updateProduct(Long productId, Product product);

    //delete
    public Product deleteProductById(Long productId);

}

```

### Código ProductServiceImpl.java (implementación)
```java
package com.api.product.service;

import jakarta.persistence.EntityNotFoundException;
import com.api.product.entity.Product;
import com.api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {  
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
    public Product updateProduct(Long productId, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            return productRepository.save(existingProduct);
        }
        return null;
//        Product existingProduct = productRepository.findById(productId)
//                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
//        //update field
//        existingProduct.setName(product.getName());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setQuantity(product.getQuantity());
//        System.out.println(product);
//        return productRepository.save(existingProduct);
//
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
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
//    }

}
```

### Código pom.xml (dependencias)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.1.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.api</groupId>
	<artifactId>product</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name/>
	<description/>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency> 
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>
	 <dependency>
	  <groupId>jakarta.persistence</groupId>
	  <artifactId>jakarta.persistence-api</artifactId>
	  <version>3.2.0</version>
	  <type>jar</type>
	 </dependency>
	 <dependency>
	  <groupId>org.springframework.data</groupId>
	  <artifactId>spring-data-commons</artifactId>
	  <version>4.1.1</version>
	  <type>jar</type>
	 </dependency>
	 <dependency>
	  <groupId>org.springframework.data</groupId>
	  <artifactId>spring-data-jpa</artifactId>
	  <version>4.1.1</version>
	  <type>jar</type>
	 </dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<executions>
					<execution>
						<id>default-compile</id>
						<phase>compile</phase>
						<goals>
							<goal>compile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
					<execution>
						<id>default-testCompile</id>
						<phase>test-compile</phase>
						<goals>
							<goal>testCompile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
</project>

```

### Código ProductApplication.java
```java
package com.api.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}

```
