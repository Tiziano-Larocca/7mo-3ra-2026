# Laboratorio Patrón MVC (Model-View-Controller)

Alumno: **Tiziano Larocca**

Profesor: **Vicente Cersósimo**

Curso: **7mo 3ra**

## Objetivo del trabajo

Implementar una aplicación web funcional aplicando el patrón de diseño MVC utilizando Servlets como controladores y JSP como vistas, simulando el módulo de inventario de un sistema de gestión.

## Parte 1 - Creación del proyecto y capa modelo

Creamos el proyecto en `Maven` como web application.

![img1](img/1.png)

Ahora añadimos las dependencias necesarias para el proyecto. Sin ellas el proyecto no podría probarse, compilarse o ejecutarse.

![img2](img/2.png)

Creamos las clases necesarias para la lógica del proyecto. Estas están creadas dentro del paquete com.techstore.modelo. `Producto.java` representa un artículo del inventario. `ProductoDAO.java` se encarga de comunicar a la aplicación con una base de datos. Esto se simula con una lista estática. Por último, `MainTest.java` sirve para verificar que todo funciona correctamente, muestra la lista de productos y el tamaño del inventario.

![img3](img/3.png)

### Código Producto.java

```java
package com.techstore.modelo;

public class Producto {
    private int    id;
    private String nombre;
    private String categoria;
    private double precio;
    private int    stock;
    // Constructor vacío
    public Producto() {}
    // Constructor completo
    public Producto(int id, String nombre, String categoria, double precio, int stock) {
        this.id        = id;
        this.nombre    = nombre;
        this.categoria = categoria;
        this.precio    = precio;
        this.stock     = stock;
    }
    // Getters y Setters
    public int    getId()        { return id; }
    public void   setId(int id)  { this.id = id; }
    public String getNombre()    { return nombre; }
    public void   setNombre(String nombre) { this.nombre = nombre; }
    public String getCategoria() { return categoria; }
    public void   setCategoria(String c) { this.categoria = c; }
    public double getPrecio()    { return precio; }
    public void   setPrecio(double precio) { this.precio = precio; }
    public int    getStock()     { return stock; }
    public void   setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio + ", stock=" + stock + '}';
    }
    
}
```

### Código ProductoDAO.java

```java
package com.techstore.modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductoDAO {
    // Simula una base de datos en memoria
    private static List<Producto> inventario = new ArrayList<>();
    private static AtomicInteger contadorId = new AtomicInteger(1);

    // Datos iniciales de TechStore
    static {
        inventario.add(new Producto(contadorId.getAndIncrement(),
            "Laptop Dell XPS 15", "Computadoras", 1299.99, 15));
        inventario.add(new Producto(contadorId.getAndIncrement(),
            "Mouse Logitech MX Master 3", "Periféricos", 89.99, 42));
        inventario.add(new Producto(contadorId.getAndIncrement(),
            "Monitor Samsung 27\"", "Monitores", 349.99, 8));
        inventario.add(new Producto(contadorId.getAndIncrement(),
            "Teclado Mecánico Keychron K2", "Periféricos", 119.99, 25));
    }

    // CRUD: Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(inventario);
    }

    // CRUD: Buscar por ID
    public Producto obtenerPorId(int id) {
        return inventario.stream()
            .filter(p -> p.getId() == id)
            .findFirst().orElse(null);
    }

    // CRUD: Agregar producto
    public void agregar(Producto p) {
        p.setId(contadorId.getAndIncrement());
        inventario.add(p);
    }

    // CRUD: Eliminar por ID
    public boolean eliminar(int id) {
        return inventario.removeIf(p -> p.getId() == id);
    }
}
```

### Código MainTest.java

```java
package com.techstore.modelo;

import com.techstore.modelo.ProductoDAO;

public class MainTest {
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();
        
        // Imprimir el tamaño del inventario
        System.out.println("Tamaño del inventario: " + dao.obtenerTodos().size());
        
        // Imprimir el contenido de la lista
        System.out.println("Lista de productos: " + dao.obtenerTodos().toString());
    }
}
```
