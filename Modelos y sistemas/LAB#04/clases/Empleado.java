package clases;

import interfaces.Evaluable;
import interfaces.Pagable;

public abstract class Empleado implements Evaluable, Pagable{
    protected static Integer id;
    protected String nombre;

    public Empleado() {}

    public Empleado(Integer id, String nombre) {
        id++;
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public float evaluar() {return 0;}

    @Override
    public void getNivelDesempeno() {}

    @Override
    public void calcularSueldo() {}

    @Override
    public void generarRecibo() {}

    public void getInfo() {}

    public String toString() {
        return "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
