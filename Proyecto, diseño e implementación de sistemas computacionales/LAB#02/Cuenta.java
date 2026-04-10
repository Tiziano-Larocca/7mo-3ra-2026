public class Cuenta {
    private String nombre;
    private String numero_cuenta;
    private double interes;
    private double saldo;

    public Cuenta() {}

    public Cuenta(String nombre, String numero_cuenta, double interes, double saldo) {
        this.nombre = nombre;
        this.numero_cuenta = numero_cuenta;
        this.interes = interes;
        this.saldo = saldo;
    }

    public boolean ingreso(double dinero) {
        if(dinero >= 0) {
            saldo += dinero;
            return true;
        }
        return false;    
    }

    public boolean extraccion(double dinero) {
        if(dinero <= saldo) {
            saldo -= dinero;
            return true;
        }
        return false; 
    }

    public void transferencia(Cuenta destino, double dinero) {
        if(dinero <= saldo) {
            saldo -= dinero;
            destino.ingreso(dinero);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
