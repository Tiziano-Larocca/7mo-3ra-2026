public class Auto extends Vehiculo {
    private String modelo;

    public Auto(String motor, Integer frenos, String modelo) {
        super(motor, frenos);
        this.motor = motor;
        this.frenos = frenos;
        this.modelo = modelo;
    } 

    @Override
    public void arrancar() {
        System.out.println("El auto " + modelo + " tiene motor " + motor + " con " + frenos + " frenos");    
    }
}
