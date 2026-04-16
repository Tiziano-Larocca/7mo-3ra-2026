package dominio;

public class Ticket {
    private String nombre;
    private String apellido;
    private double montoTotal;
    private double montoCuota;

    public Ticket() {}

    public Ticket(String nombre, String apellido, double montoTotal, double montoCuota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.montoTotal = montoTotal;
        this.montoCuota = montoCuota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(double montoCuota) {
        this.montoCuota = montoCuota;
    }
}
