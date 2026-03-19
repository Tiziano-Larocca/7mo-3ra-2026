public class App {
    public static void main(String[] args) throws Exception {
        Vehiculo moto = new Moto("1.8 TURBO", 2, 1000);
        moto.arrancar();

        Vehiculo auto = new Auto("V8 BITURBO", 4, "Fiat 600");
        auto.arrancar();
    }
}
