public abstract class Vehiculo { //Clase abstracta, las demas clases heredan de el. No crea objetos.
    protected String motor;
    protected Integer frenos;
    
    public Vehiculo(String motor, Integer frenos) {
        this.motor = motor;
        this.frenos = frenos;
    }
    public abstract void arrancar();    
}
