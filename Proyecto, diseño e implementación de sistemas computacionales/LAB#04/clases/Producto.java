package clases;

public class Producto {
    private static Integer idProducto = 0;
    private static Integer contarProductos = 0;
    private String nombre;
    private double precio;

    public String toString() {
        return "Nombre: " + nombre + "\nPrecio: " + precio;
    }

    public Producto(String nombre, double precio) {
        this.idProducto = ++contarProductos;
        this.nombre = nombre;
        if(precio > 0) {
            this.precio = precio;
        }
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        if(precio > 0) {
            this.precio = precio;
        }
    }
}
