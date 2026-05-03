package clases;

public class EmpleadoFijo extends Empleado{
    private Integer antiguedad;
    private float puntaje;
    private double sueldoBase;

    public EmpleadoFijo() {}

    public EmpleadoFijo(Integer id, String nombre, Integer antiguedad, float puntaje, double sueldoBase) {
        super(id, nombre);
        this.antiguedad = antiguedad;
        this.puntaje = puntaje;
        this.sueldoBase = sueldoBase;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(float puntaje) {
        this.puntaje = puntaje;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }
}
