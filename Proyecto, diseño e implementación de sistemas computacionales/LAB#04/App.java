import clases.Ordenes;
import clases.Producto;

public class App {
    public static void main(String[] args) throws Exception {
        Producto producto1 = new Producto("Chevrolet Corsa 1.9", 150);
        Producto producto2 = new Producto("Chevrolet Astra 1.1", 120);
        Producto producto3 = new Producto("Chevrolet Onix 5.6", 100);
        Producto producto4 = new Producto("Chevrolet Cruze 10.4", 80);
        Producto producto5 = new Producto("Chevrolet Corsa 1.9", 150);
        Producto producto6 = new Producto("Chevrolet Astra 1.1", 120);
        Producto producto7 = new Producto("Chevrolet Onix 5.6", 100);
        Producto producto8 = new Producto("Chevrolet Cruze 10.4", 80);
        Producto producto9 = new Producto("Chevrolet Onix 5.6", 100);
        Producto producto10 = new Producto("Chevrolet Cruze 10.4", 80);
        Producto producto11 = new Producto("Chevrolet Cruze 10.4", 80);

        Ordenes orden = new Ordenes();
        orden.agregarProductos(producto1);
        orden.agregarProductos(producto2);
        orden.agregarProductos(producto3);
        orden.agregarProductos(producto4);
        orden.agregarProductos(producto5);
        orden.agregarProductos(producto6);
        orden.agregarProductos(producto7);
        orden.agregarProductos(producto8);
        orden.agregarProductos(producto9);
        orden.agregarProductos(producto10);
        orden.agregarProductos(producto11);

        orden.mostrarOrden();

        System.out.println("Total: $" + orden.total());
    }
}
