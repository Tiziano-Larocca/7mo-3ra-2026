# API REST --- Product y Category

Alumno: **Tiziano Larocca**

Profesor: **Vicente Cersósimo**

Curso: **7mo 3ra**

## 1. Descripción general

Esta aplicación es una API REST desarrollada con **Spring Boot**,
**Spring Data JPA**, **MySQL** y **Lombok**.

La aplicación permite realizar las operaciones CRUD sobre productos:

-   **Crear** un producto.
-   **Consultar** todos los productos.
-   **Consultar** un producto por su ID.
-   **Actualizar** un producto.
-   **Eliminar** un producto.

El flujo general de una petición es:

``` text
Cliente
   ↓
ProductController
   ↓
ProductService / ProductServiceImpl
   ↓
ProductRepository
   ↓
Base de datos MySQL
```

En el sentido contrario, la información vuelve desde la base de datos
hacia el `Repository`, luego al `Service`, después al `Controller` y
finalmente al cliente mediante una respuesta HTTP.

------------------------------------------------------------------------

# 2. Category y su relación con Product

Antes de explicar la implementación de `Product`, es importante entender
brevemente qué función cumple `Category` y cómo se relaciona con los
productos.

## 2.1 CRUD de Category

Al igual que `Product`, `Category` trabaja con las operaciones básicas
de una API REST, conocidas como **CRUD**:

| Operación | Función | Concepto |
| :--- | :--- | :--- |
| **Create** | Crear una categoría | Agrega una nueva categoría |
| **Read** | Consultar categorías | Obtiene una o varias categorías |
| **Update** | Actualizar una categoría | Modifica los datos de una categoría existente |
| **Delete** | Eliminar una categoría | Elimina una categoría del sistema o base de datos |

Estas operaciones permiten administrar las categorías que posteriormente
pueden ser asignadas a los productos.

La relación con `Product` es especialmente importante al momento de
crear un producto, porque el `ProductRequestDTO` recibe un `categoryId`.
El sistema utiliza ese ID para buscar la categoría correspondiente antes
de guardar el producto.

## 2.2 Relación 1:N entre Category y Product

La relación entre ambas entidades es **1:N (uno a muchos)**:

``` text
             CATEGORY
                 │
                 │ 1
                 │
                 │
                 │ N
        ┌────────┼────────┐
        ↓        ↓        ↓
     Product  Product  Product
```

Esto significa que:

-   Una **Category** puede tener muchos **Product**.
-   Cada **Product** pertenece a una sola **Category**.

Desde el lado de `Product`, la relación se representa mediante:

``` java
@ManyToOne
@JoinColumn(name = "category_id")
private Category category;
```

`@ManyToOne` indica que muchos productos pueden estar asociados a una
misma categoría.

`@JoinColumn(name = "category_id")` indica que la tabla `products`
utiliza `category_id` como clave foránea para establecer la relación.

Por ejemplo:

``` text
Category
ID: 2
Nombre: Periféricos
        │
        ├── Product 1: Mouse
        ├── Product 2: Teclado
        └── Product 3: Auriculares
```

Por lo tanto, varios productos pueden tener:

``` text
category_id = 2
```

## 2.3 Relación durante la creación de Product

Cuando se crea un producto, el cliente envía el ID de la categoría:

``` json
{
  "name": "Mouse",
  "price": 25000,
  "quantity": 10,
  "categoryId": 2
}
```

El Service busca la categoría mediante ese ID:

``` java
Category category = categoryRepository.findById(dto.categoryId())
    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
```

Si la categoría existe, se asigna al producto:

``` java
product.setCategory(category);
```

Finalmente, el producto se guarda junto con la referencia a su
categoría.

Si la categoría no existe, el producto no puede establecer correctamente
la relación y la creación falla.

# 2. Organización de Product por capas

El recurso `Product` está dividido en varias capas:

``` text
Product
├── entity
│   └── Product.java
│
├── dto
│   ├── ProductRequestDTO.java
│   └── ProductResponseDTO.java
│
├── repository
│   └── ProductRepository.java
│
├── service
│   ├── ProductService.java
│   └── ProductServiceImpl.java
│
└── controller
    └── ProductController.java
```

Cada capa tiene una responsabilidad diferente.

| Capa | Archivo | Responsabilidad |
| :--- | :--- | :--- |
| **Entity** | `Product.java` | Representa el producto y su estructura en la base de datos |
| **DTO** | `ProductRequestDTO.java` | Recibe los datos enviados por el cliente |
| **DTO** | `ProductResponseDTO.java` | Define los datos que se devuelven al cliente |
| **Repository** | `ProductRepository.java` | Accede a la base de datos |
| **Service** | `ProductService.java` | Define las operaciones que puede realizar Product |
| **Service** | `ProductServiceImpl.java` | Contiene la lógica de esas operaciones |
| **Controller** | `ProductController.java` | Recibe las peticiones HTTP y devuelve respuestas |

# 4. Entity --- Product.java

La entidad `Product` representa un producto dentro de la aplicación y
también su correspondiente registro en la base de datos.

``` java
@Entity
@Table(name = "products")
public class Product {
```

`@Entity` indica a JPA que esta clase es una entidad persistente.

`@Table(name = "products")` indica que los objetos `Product` se
almacenan en la tabla `products`.

## 3.1 Atributos

La entidad posee los siguientes atributos:

``` java
private Long id;
private String name;
private BigDecimal price;
private Integer quantity;
private Category category;
```

### `id`

``` java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

Es el identificador único del producto.

-   `@Id` indica que es la clave primaria.
-   `@GeneratedValue` hace que el ID sea generado automáticamente por la
    base de datos.

### `name`

``` java
private String name;
```

Guarda el nombre del producto.

### `price`

``` java
private BigDecimal price;
```

Guarda el precio del producto.

Se utiliza `BigDecimal` porque es apropiado para trabajar con valores
monetarios evitando los problemas de precisión que pueden aparecer con
tipos de punto flotante.

### `quantity`

``` java
private Integer quantity;
```

Representa la cantidad disponible del producto.

------------------------------------------------------------------------

# 5. Relación 1:N entre Category y Product

Existe una relación **1:N (uno a muchos)**.

La idea es:

``` text
Una categoría
      │
      ├── Producto 1
      ├── Producto 2
      ├── Producto 3
      └── Producto N
```

Por lo tanto:

> Una categoría puede tener muchos productos, mientras que cada producto
> pertenece a una categoría.

Desde el lado de `Product` se encuentra:

``` java
@ManyToOne
@JoinColumn(name = "category_id")
private Category category;
```

Aunque conceptualmente la relación completa es 1:N, desde `Product` se
observa el lado **N**, porque muchos productos pueden apuntar a la misma
categoría.

## 4.1 `@ManyToOne`

``` java
@ManyToOne
```

Indica que muchos objetos `Product` pueden estar relacionados con un
mismo objeto de categoría.

Ejemplo:

``` text
Categoría: Electrónica

    ↑
    │
    ├── Mouse
    ├── Teclado
    ├── Monitor
    └── Auriculares
```

Los cuatro productos pueden pertenecer a la misma categoría.

## 4.2 `@JoinColumn`

``` java
@JoinColumn(name = "category_id")
```

Indica que la relación se almacena mediante una columna llamada
`category_id`.

Por ejemplo, conceptualmente la tabla `products` puede tener:

| id | name | price | quantity | category_id |
| ---: | :--- | ---: | ---: | ---: |
| 1 | Mouse | 25000 | 10 | 2 |
| 2 | Teclado | 40000 | 5 | 2 |
| 3 | Monitor | 250000 | 3 | 4 |


En este caso, los productos 1 y 2 pertenecen a la misma categoría porque
tienen el mismo `category_id`.

------------------------------------------------------------------------

# 6. DTO de entrada --- ProductRequestDTO

`ProductRequestDTO` se utiliza para recibir información desde el
cliente.

Está definido como un `record`:

``` java
public record ProductRequestDTO(
    String name,
    BigDecimal price,
    int quantity,
    Long categoryId
) {}
```

Contiene:

| Campo | Tipo | Función |
| :--- | :--- | :--- |
| `name` | `String` | Nombre del producto |
| `price` | `BigDecimal` | Precio |
| `quantity` | `int` | Cantidad |
| `categoryId` | `Long` | ID de la categoría |

## 5.1 Validaciones

El nombre tiene:

``` java
@NotBlank
@Size(min = 2, max = 50)
```

Por lo tanto, no puede estar vacío y debe tener entre 2 y 50 caracteres.

El precio tiene:

``` java
@NotNull
@Positive
```

Por lo tanto, debe existir y ser positivo.

La cantidad tiene:

``` java
@Min(0)
@Positive
```

En la implementación actual, `@Positive` hace que el valor tenga que ser
mayor que cero.

------------------------------------------------------------------------

# 7. DTO de salida --- ProductResponseDTO

`ProductResponseDTO` define la información que se devuelve al cliente.

Contiene:

``` java
private Long id;
private String name;
private BigDecimal price;
private Integer quantity;
private String categoryName;
```

A diferencia del objeto de entrada, el cliente recibe `categoryName` en
lugar del objeto completo `Category`.

Ejemplo de respuesta:

``` json
{
  "id": 1,
  "name": "Mouse",
  "price": 25000,
  "quantity": 10,
  "categoryName": "Periféricos"
}
```

Esto permite controlar qué información de la entidad se expone en la
respuesta.

------------------------------------------------------------------------

# 8. Repository --- ProductRepository

El repository es:

``` java
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

Hereda de `JpaRepository`, por lo que Spring Data JPA proporciona
automáticamente operaciones para trabajar con `Product`.

Entre las operaciones utilizadas se encuentran:

``` java
save(product)
findAll()
findById(id)
deleteById(id)
```

No es necesario escribir manualmente las consultas SQL básicas para
estas operaciones.

El repository es la capa que comunica el Service con la base de datos.

------------------------------------------------------------------------

# 9. Service --- ProductService

`ProductService` es una interfaz que define qué operaciones puede
realizar el sistema sobre los productos.

``` java
public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO dto);

    List<Product> getAllProducts();

    ProductResponseDTO getProductById(Long productId);

    Product updateProduct(Long productId, ProductRequestDTO dto);

    Product deleteProductById(Long productId);
}
```

Las operaciones se agrupan conceptualmente en CRUD:

| Operación | Método |
| :--- | :--- |
| **Create** | `createProduct()` |
| **Read todos** | `getAllProducts()` |
| **Read uno** | `getProductById()` |
| **Update** | `updateProduct()` |
| **Delete** | `deleteProductById()` |

La interfaz define el contrato, mientras que `ProductServiceImpl`
contiene la implementación.

------------------------------------------------------------------------

# 10. ServiceImpl --- ProductServiceImpl

`ProductServiceImpl` contiene la lógica principal del recurso `Product`.

Tiene acceso a los repositories mediante inyección de dependencias:

``` java
@Autowired
private ProductRepository productRepository;

@Autowired
private CategoryRepository categoryRepository;
```

`ProductRepository` se utiliza para trabajar con productos.

`CategoryRepository` se utiliza para buscar la categoría correspondiente
cuando se crea un producto.

------------------------------------------------------------------------

# 11. Crear / guardar un producto

El método utilizado es:

``` java
createProduct(ProductRequestDTO dto)
```

El proceso es el siguiente.

## Paso 1 --- Buscar la categoría

Primero se utiliza el `categoryId` recibido:

``` java
Category category = categoryRepository.findById(dto.categoryId())
    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
```

El sistema busca la categoría en la base de datos.

Si no existe, se lanza una excepción y el producto no se crea.

## Paso 2 --- Crear el Product

Se crea un nuevo objeto:

``` java
Product product = new Product();
```

Después se copian los datos recibidos:

``` java
product.setName(dto.name());
product.setPrice(dto.price());
product.setQuantity(dto.quantity());
product.setCategory(category);
```

En este momento se establece también la relación entre el producto y la
categoría.

## Paso 3 --- Guardar

Se ejecuta:

``` java
Product saved = productRepository.save(product);
```

El `Repository` guarda el producto en la base de datos.

Como el ID se genera automáticamente, después de guardar el objeto
`saved` contiene el ID generado.

## Paso 4 --- Crear la respuesta

Finalmente se crea un `ProductResponseDTO`:

``` java
ProductResponseDTO response = new ProductResponseDTO();
response.setId(saved.getId());
response.setName(saved.getName());
response.setPrice(saved.getPrice());
response.setQuantity(saved.getQuantity());
response.setCategoryName(saved.getCategory().getName());
```

El método devuelve ese DTO.

### Flujo completo de guardar

``` text
POST /api/v3/products
        ↓
ProductController.create()
        ↓
ProductServiceImpl.createProduct()
        ↓
Buscar categoría
        ↓
Crear Product
        ↓
productRepository.save()
        ↓
Crear ProductResponseDTO
        ↓
HTTP 201 CREATED
```

------------------------------------------------------------------------

# 12. Consultar todos los productos

El método es:

``` java
getAllProducts()
```

Su implementación es:

``` java
return productRepository.findAll();
```

`findAll()` obtiene todos los registros de `products`.

El Controller expone esta operación mediante:

``` java
@GetMapping("/products")
```

Por lo tanto:

``` text
GET /api/v3/products
```

devuelve todos los productos.

En la implementación actual, este endpoint devuelve directamente:

``` java
List<Product>
```

y no `List<ProductResponseDTO>`.

------------------------------------------------------------------------

# 13. Buscar un producto por ID

El método es:

``` java
getProductById(Long productId)
```

Se utiliza:

``` java
productRepository.findById(productId)
```

Este método devuelve un `Optional<Product>`.

Después se transforma el resultado en un `ProductResponseDTO`.

``` java
.map(product -> {
    ProductResponseDTO response = new ProductResponseDTO();

    response.setId(product.getId());
    response.setName(product.getName());
    response.setPrice(product.getPrice());
    response.setQuantity(product.getQuantity());
    response.setCategoryName(product.getCategory().getName());

    return response;
})
```

Si el producto no existe:

``` java
.orElseThrow(
    () -> new EntityNotFoundException(
        "Product not found with id: " + productId
    )
);
```

se lanza una `EntityNotFoundException`.

El Controller captura esta excepción y devuelve:

``` text
HTTP 404 NOT FOUND
```

Endpoint:

``` text
GET /api/v3/products/{id}
```

Ejemplo:

``` text
GET /api/v3/products/5
```

------------------------------------------------------------------------

# 14. Actualizar un producto

El método utilizado es:

``` java
updateProduct(Long productId, ProductRequestDTO dto)
```

Primero busca el producto:

``` java
Optional<Product> optionalProduct =
    productRepository.findById(productId);
```

Si existe:

``` java
Product existingProduct = optionalProduct.get();
```

se actualizan sus datos:

``` java
existingProduct.setName(dto.name());
existingProduct.setPrice(dto.price());
existingProduct.setQuantity(dto.quantity());
```

Después se guarda:

``` java
return productRepository.save(existingProduct);
```

El endpoint utilizado es:

``` text
PUT /api/v3/products/{id}
```

Por ejemplo:

``` text
PUT /api/v3/products/5
```

con un cuerpo JSON como:

``` json
{
  "name": "Mouse Gamer",
  "price": 30000,
  "quantity": 15,
  "categoryId": 2
}
```

## Importante sobre la implementación actual

Aunque `ProductRequestDTO` recibe `categoryId`, el método
`updateProduct()` **no cambia la categoría del producto**.

Actualmente sólo actualiza:

-   `name`
-   `price`
-   `quantity`

El `categoryId` sólo se utiliza durante `createProduct()` para
establecer la relación inicial.

Si se quisiera permitir cambiar la categoría durante una actualización,
habría que buscar la nueva categoría y ejecutar algo equivalente a:

``` java
existingProduct.setCategory(category);
```

------------------------------------------------------------------------

# 15. Eliminar un producto

El método es:

``` java
deleteProductById(Long productId)
```

Primero busca el producto:

``` java
Optional<Product> optionalProduct =
    productRepository.findById(productId);
```

Si existe:

``` java
productRepository.deleteById(productId);
```

El producto se elimina de la base de datos.

El endpoint es:

``` text
DELETE /api/v3/products/{id}
```

Ejemplo:

``` text
DELETE /api/v3/products/5
```

El Controller devuelve:

``` text
Product deleted successfully
```

con estado:

``` text
HTTP 200 OK
```

## ¿Por qué primero busca el producto?

Porque el Service comprueba si existe antes de ejecutar:

``` java
deleteById(productId)
```

Si no existe, el método devuelve `null`.

Sin embargo, el Controller actual no comprueba ese `null` y devuelve
igualmente el mensaje de eliminación exitosa.

------------------------------------------------------------------------

# 16. Controller --- ProductController

El Controller es la puerta de entrada HTTP de `Product`.

Está definido como:

``` java
@RestController
@RequestMapping("/api/v3")
```

Por lo tanto, todos los endpoints comienzan con:

``` text
/api/v3
```

El Controller utiliza:

``` java
@Autowired
private ProductService productService;
```

Esto permite que el Controller utilice la lógica definida por el Service
sin acceder directamente al Repository.

------------------------------------------------------------------------

# 17. Endpoints de Product

| Método HTTP | Endpoint | Operación |
| :--- | :--- | :--- |
| `POST` | `/api/v3/products` | Crear producto |
| `GET` | `/api/v3/products` | Obtener todos |
| `GET` | `/api/v3/products/{id}` | Obtener uno |
| `PUT` | `/api/v3/products/{id}` | Actualizar |
| `DELETE` | `/api/v3/products/{id}` | Eliminar |

# 18. POST --- Crear producto

El Controller recibe:

``` java
@PostMapping("/products")
public ResponseEntity<ProductResponseDTO> create(
    @RequestBody ProductRequestDTO dto
)
```

`@RequestBody` convierte el JSON enviado por el cliente en un
`ProductRequestDTO`.

Después:

``` java
ProductResponseDTO created =
    productService.createProduct(dto);
```

El Controller delega la lógica al Service.

Finalmente devuelve:

``` java
ResponseEntity
    .status(HttpStatus.CREATED)
    .body(created);
```

Por lo tanto, una creación exitosa devuelve:

``` text
HTTP 201 CREATED
```

------------------------------------------------------------------------

# 19. GET --- Obtener todos

El método es:

``` java
@GetMapping("/products")
public ResponseEntity<List<Product>> getAllProducts()
```

Llama a:

``` java
productService.getAllProducts()
```

El Service utiliza:

``` java
productRepository.findAll()
```

y devuelve los productos.

------------------------------------------------------------------------

# 20. GET --- Obtener por ID

El endpoint:

``` java
@GetMapping("products/{id}")
```

recibe el ID mediante:

``` java
@PathVariable Long id
```

Después llama:

``` java
productService.getProductById(id)
```

Si existe, devuelve:

``` text
HTTP 200 OK
```

Si no existe, se genera:

``` text
HTTP 404 NOT FOUND
```

------------------------------------------------------------------------

# 21. PUT --- Actualizar

El endpoint es:

``` java
@PutMapping("/products/{id}")
```

Recibe:

-   El ID mediante `@PathVariable`.
-   Los nuevos datos mediante `@RequestBody`.

Primero comprueba que el producto exista mediante:

``` java
productService.getProductById(id)
```

Después llama:

``` java
productService.updateProduct(id, dto)
```

y devuelve el producto actualizado con:

``` text
HTTP 200 OK
```

------------------------------------------------------------------------

# 22. DELETE --- Borrar

El endpoint:

``` java
@DeleteMapping("/products/{id}")
```

recibe el ID y ejecuta:

``` java
productService.deleteProductById(id);
```

Después devuelve:

``` text
HTTP 200 OK
```

con:

``` text
Product deleted successfully
```

------------------------------------------------------------------------

# 23. Manejo de errores

El Controller tiene un manejador para:

``` java
@ExceptionHandler(EntityNotFoundException.class)
```

Cuando se lanza una `EntityNotFoundException`, se devuelve:

``` java
ResponseEntity
    .status(HttpStatus.NOT_FOUND)
    .body(ex.getMessage());
```

Por lo tanto, cuando se intenta consultar un producto inexistente, la
API devuelve:

``` text
404 NOT FOUND
```

------------------------------------------------------------------------

# 24. Flujo completo de una petición

Por ejemplo, para crear un producto:

``` text
Cliente
  │
  │ POST /api/v3/products
  │
  ▼
ProductController
  │
  │ ProductRequestDTO
  ▼
ProductService
  │
  ▼
ProductServiceImpl
  │
  ├── Busca la categoría
  │
  ├── Crea Product
  │
  └── productRepository.save()
  │
  ▼
ProductRepository
  │
  ▼
MySQL
  │
  │ Producto guardado
  ▼
ProductServiceImpl
  │
  └── ProductResponseDTO
  ▼
ProductController
  │
  │ HTTP 201 CREATED
  ▼
Cliente
```

------------------------------------------------------------------------

# 25. Resumen de responsabilidades

## Entity

`Product.java`

Representa el producto y su relación con una categoría.

Se encarga de mapear los objetos Java con la tabla `products`.

## DTO

`ProductRequestDTO.java`

Representa los datos que el cliente envía a la API.

`ProductResponseDTO.java`

Representa los datos que la API devuelve al cliente.

## Repository

`ProductRepository.java`

Se comunica con la base de datos mediante Spring Data JPA.

Proporciona operaciones como:

``` text
save()
findAll()
findById()
deleteById()
```

## Service

`ProductService.java`

Define las operaciones disponibles para Product.

## ServiceImpl

`ProductServiceImpl.java`

Implementa la lógica:

``` text
Crear
Consultar
Actualizar
Eliminar
```

También se encarga de buscar la categoría cuando se crea un producto.

## Controller

`ProductController.java`

Expone las operaciones mediante endpoints HTTP y transforma las
peticiones en llamadas al Service.

------------------------------------------------------------------------

# 26. Resumen CRUD

``` text
CREATE
POST /api/v3/products
        ↓
createProduct()
        ↓
save()

READ
GET /api/v3/products
        ↓
getAllProducts()
        ↓
findAll()

READ ONE
GET /api/v3/products/{id}
        ↓
getProductById()
        ↓
findById()

UPDATE
PUT /api/v3/products/{id}
        ↓
updateProduct()
        ↓
findById()
        ↓
save()

DELETE
DELETE /api/v3/products/{id}
        ↓
deleteProductById()
        ↓
findById()
        ↓
deleteById()
```

------------------------------------------------------------------------

# 27. Conclusión

La aplicación utiliza una arquitectura por capas para separar
responsabilidades.

El **Controller** recibe las peticiones HTTP, el **Service** contiene la
lógica de negocio, el **Repository** se encarga del acceso a datos y la
**Entity** representa la información persistida.

Los DTO permiten separar los datos que recibe la API de los datos que
devuelve.

En cuanto a la relación, cada `Product` pertenece a una categoría
mediante `@ManyToOne`, mientras que conceptualmente una categoría puede
contener muchos productos. La clave foránea `category_id` permite
representar esta relación 1:N en la base de datos.

El flujo principal puede resumirse como:

``` text
HTTP Request
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
MySQL
     ↓
Repository
     ↓
Service
     ↓
Controller
     ↓
HTTP Response
```
