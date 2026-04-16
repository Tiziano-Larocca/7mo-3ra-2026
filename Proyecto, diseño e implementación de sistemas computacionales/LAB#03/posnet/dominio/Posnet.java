package dominio;

public class Posnet {
    public final double RECARGO_CUOTA = 0.03;
    public final Integer MIN_CUOTA = 1;
    public final Integer MAX_CUOTA = 6;
  
    
    public Ticket efectuarPago(TarjetaDeCredito tarjetaCredito, double monto, int cantidadCuotas) {
        Ticket ticket = null;

        if(datosValidos(tarjetaCredito, monto, cantidadCuotas)) {
            double montoTotal = monto + monto * recargoCuotas(cantidadCuotas);
            if(tarjetaCredito.getSaldo() >= montoTotal) {
                tarjetaCredito.setSaldo(tarjetaCredito.getSaldo() - montoTotal);
                ticket = new Ticket(tarjetaCredito.getTitular().getNombre(), tarjetaCredito.getTitular().getApellido(), montoTotal, montoTotal / cantidadCuotas);
            } 
        }
        return ticket;
    }
    
    private boolean datosValidos(TarjetaDeCredito tarjetaCredito, double monto, int cantidadCuotas) {
        boolean tarjetaValida = tarjetaCredito != null;
        boolean montoValido = monto > 0;
        boolean cuotaValida = cantidadCuotas >= MIN_CUOTA && cantidadCuotas <= MAX_CUOTA;
        return tarjetaValida && montoValido && cuotaValida;
    }
    
    private double recargoCuotas(int cuotas) {
        return (cuotas - 1) * RECARGO_CUOTA;
    }
}
