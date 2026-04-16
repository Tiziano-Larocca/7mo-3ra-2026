import dominio.Posnet;
import dominio.TarjetaDeCredito;
import dominio.Ticket;
import dominio.Titular;

public class App {
    public static void main(String[] args) throws Exception {
        Posnet posnet = new Posnet();

        Titular t = new Titular("12345678", "Tiziano", "Larocca", "123456789", "laroccatiziano1@gmail.com");
        TarjetaDeCredito tarjeta = new TarjetaDeCredito("Banco Nacion", "5348-4769", 15000.0, t);
        Ticket ticket = posnet.efectuarPago(tarjeta, 10000.0, 5);
        
        System.out.println(ticket.getNombre() + " " + ticket.getApellido());
        System.out.println(ticket.getMontoTotal());
        System.out.println(ticket.getMontoCuota());
    }
}