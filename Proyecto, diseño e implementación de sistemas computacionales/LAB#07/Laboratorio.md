# Laboratorio Patrón MVC (Model-View-Controller)

Alumno: **Tiziano Larocca**

Profesor: **Vicente Cersósimo**

Curso: **7mo 3ra**

## Objetivo del trabajo

Implementar una aplicación web funcional aplicando el patrón de diseño `MVC` utilizando `Servlets` como controladores y `JSP` como vistas, simulando el módulo de inventario de un sistema de gestión.

## Parte 1 - Creación del proyecto y capa Modelo

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

El código funciona correctamente.
![img4](img/4.png)

## Preguntas de reflexión - Capa Modelo
1. ¿Por qué el DAO usa una lista estática (static)? ¿Qué implicación tiene esto en un servidor con múltiples usuarios concurrentes?
    * El DAO usa una lista estática para que todos los objetos DAO compartan los mismos datos en memoria, para simular una base de datos simple. En un servidor con múltiples usuarios implica que todos acceden y modifican la lista compartida.
  
2. ¿Qué cambios mínimos serían necesarios para que ProductoDAO se conecte a una base de datos MySQL real usando JDBC?
    * Para que ProductoDAO se conecte a una base de datos MySQL debería agregar el driver JDBC de MySQL en pom.xml, crear la conexión JDBC en ProductoDAO, reemplazar los métodos CRUD para que ejecuten consultas SQL y crear las tablas.

3. ¿Por qué se devuelve new ArrayList<>(inventario) en obtenerTodos() en lugar de devolver la referencia directamente?
    * Se devuelve new ArrayList<> en vez de devolver la referencia directamente para proteger los datos en el DAO. El código que recibe la lista podría modificar directamente el inventario, lo que rompe el principio del encapsulamiento porque cualquier clase podría alterar el estado interno del DAO.
  
## Parte 2 - Controlador Servlet

Ahora implementaremos el `Servlet`, que actúa como controlador principal de la aplicación. El Servlet interceptará todas las peticiones relacionadas con productos, llamará al DAO correspondiente y decidirá a qué vista redirigir. Este archivo se encontrará en otro paquete llamado `com.techstore.controlador`

![img5](img/5.png)

### Código ProductoServlet.java

```java
package com.techstore.controlador;


import com.techstore.modelo.Producto;
import com.techstore.modelo.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Mapeo mediante anotación (alternativa a web.xml)
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {


    private ProductoDAO dao = new ProductoDAO();


    // ── GET: mostrar lista o formulario ──────────────────────────
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {


        String accion = req.getParameter("accion");
        if (accion == null) accion = "listar";


        switch (accion) {
            case "listar":
                listar(req, resp);
                break;
            case "nuevo":
                req.getRequestDispatcher(
                    "/WEB-INF/vistas/formulario.jsp")
                   .forward(req, resp);
                break;
            case "eliminar":
                eliminar(req, resp);
                break;
            case "buscar":
                buscar(req, resp);
                break;
            default:
                listar(req, resp);
        }
    }
        // ── POST: procesar formulario ─────────────────────────────────
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        // Patrón PRG: Post → Redirect → Get
        req.setCharacterEncoding("UTF-8");
        String nombre    = req.getParameter("nombre");
        String categoria = req.getParameter("categoria");
        double precio    = Double.parseDouble(req.getParameter("precio"));
        int    stock     = Integer.parseInt(req.getParameter("stock"));
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setCategoria(categoria);
        p.setPrecio(precio);
        p.setStock(stock);
        dao.agregar(p);
        // Redirect evita reenvío del formulario (PRG)
        resp.sendRedirect(req.getContextPath() + "/productos");
    }
    // ── Métodos privados auxiliares ───────────────────────────────
    private void listar(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        List<Producto> lista = dao.obtenerTodos();
        req.setAttribute("productos", lista);
        req.getRequestDispatcher("/WEB-INF/vistas/lista.jsp")
           .forward(req, resp);
    }
    private void eliminar(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        dao.eliminar(id);
        resp.sendRedirect(req.getContextPath() + "/productos");
    }
    private void buscar(HttpServletRequest req,
                    HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        Producto producto = dao.obtenerPorId(id);

        List<Producto> lista = new ArrayList<>();

        if (producto != null) {
            lista.add(producto);
        }

        req.setAttribute("productos", lista);

        req.getRequestDispatcher("/WEB-INF/vistas/lista.jsp")
           .forward(req, resp);
    }
}
```


