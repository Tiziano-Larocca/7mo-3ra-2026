public class Moto extends Vehiculo {
    private Integer cilindrada;

    public Moto(String motor, Integer frenos, Integer cilindrada) {
        super(motor, frenos);
        this.motor = motor;
        this.frenos = frenos;
        this.cilindrada = cilindrada;
    } 

    @Override
    public void arrancar() {
        System.out.println("La moto con motor " + motor + " arranca con patada. La moto tiene " + frenos + " frenos y una cilindrada de " + cilindrada);    
    }
}
