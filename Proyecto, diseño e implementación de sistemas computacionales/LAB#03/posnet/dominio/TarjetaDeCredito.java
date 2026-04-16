package dominio;

public class TarjetaDeCredito {
    private String nombreBanco;
    private String numeroTarjeta;
    private double saldo;
    private Titular titular;

    public TarjetaDeCredito() {}

    public TarjetaDeCredito(String nombreBanco, String numeroTarjeta, double saldo, Titular titular) {
        this.nombreBanco = nombreBanco;
        this.numeroTarjeta = numeroTarjeta;
        this.saldo = saldo;
        this.titular = titular;
    }

    /*public boolean tieneSaldo(double saldo) {}
    public void descontar(double monto) {}
    public String nombreTitular() {}
    public String toString() {}*/

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }
}
