package clases;

public class EmpleadoPorHoras extends Empleado{
    private Integer horasTrabajadas;
    private float puntaje;
    private double valorHora;

    public EmpleadoPorHoras() {}

    public EmpleadoPorHoras(Integer horasTrabajadas, float puntaje, double valorHora, Integer id, String nombre) {
        super(id, nombre);
        this.horasTrabajadas = horasTrabajadas;
        this.puntaje = puntaje;
        this.valorHora = valorHora;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(float puntaje) {
        this.puntaje = puntaje;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }
}
