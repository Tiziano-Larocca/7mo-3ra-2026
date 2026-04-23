package clases;

public class Ordenes {
    private static Integer idOrden = 0;
    private static Integer contarOrdenes = 0;
    private double cuentaTotal = 0;
    private Integer contarProductos = 0;
    private final Integer MAXPRODUCTOS = 10;
    private Producto productos[] = new Producto[10];

    public Ordenes() {
        //idOrden++; //Creo una orden e incremento ID.
        this.idOrden = ++contarOrdenes;
    }
    
    public void agregarProductos(Producto producto) {
        if(contarProductos < MAXPRODUCTOS) {
            productos[contarProductos] = producto;
            contarProductos++;
            cuentaTotal += producto.getPrecio();
        } else {
            System.out.println("No se pueden agregar más productos a la orden (máximo 10).");
        }
    }

    public void mostrarOrden() {
        for(Integer i = 0; i < contarProductos; i++) {
            System.out.println(productos[i].getNombre() + " $" + productos[i].getPrecio());     
        }
    }

    public double total(){
        return cuentaTotal;
    }
}
