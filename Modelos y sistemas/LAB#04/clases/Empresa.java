package clases;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Empleado> lista_empleados;
    private String nombre;

    public Empresa(String nombre) {
        this.lista_empleados = new ArrayList<>();
        this.nombre = nombre;
    }

    public void agregarEmpleado() {}

    public void calcularNomina() {}
    
    public void listarEmpleados() {}

    public void reportePorDepto() {}

    public List<Empleado> getLista_empleados() {
        return lista_empleados;
    }

    public void setLista_empleados(List<Empleado> lista_empleados) {
        this.lista_empleados = lista_empleados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
