public class App {
    public static void main(String[] args) throws Exception {
        //PRUEBA INGRESO
        System.out.println("INGRESO");
        Cuenta cuenta1 = new Cuenta();
        System.out.println(cuenta1.getSaldo());
        cuenta1.ingreso(100.0);
        System.out.println(cuenta1.getSaldo());
        cuenta1.ingreso(-100.0);
        System.out.println(cuenta1.getSaldo());

        //PRUEBA EXTRACCIÓN
        System.out.println("EXTRACCION");
        Cuenta cuenta2 = new Cuenta("Luca Bagnato", "1234", 0.0, 250.0);
        cuenta2.extraccion(200.0);
        System.out.println(cuenta2.getSaldo());
        cuenta2.extraccion(100.0);
        System.out.println(cuenta2.getSaldo());

        //PRUEBA TRANSFERENCIA
        System.out.println("TRANSFERENCIA");
        Cuenta origen = new Cuenta("Tomas Cavallaro", "5678", 0.0, 300.0);
        Cuenta destino = new Cuenta("Vicente Cersosimo", "9101", 0.0, 450.0);
        origen.transferencia(destino, 100.0);
        System.out.println(origen.getSaldo());
        System.out.println(destino.getSaldo());
    }
}
