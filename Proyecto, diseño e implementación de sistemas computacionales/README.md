# Java-POO-Pilares
# Los 4 pilares de la Programación Orientada a Objetos (POO) en Java

------------------------------------------------------------------------

## Introducción

La **Programación Orientada a Objetos (POO)** es un paradigma de
programación que organiza el software alrededor de **objetos**, los
cuales representan entidades del mundo real.

Los objetos combinan:

-   **Datos (atributos)**
-   **Comportamientos (métodos)**

La POO se basa en **cuatro pilares fundamentales** que permiten crear
software más organizado, reutilizable y fácil de mantener.

Estos pilares son:

1.  Encapsulación\
2.  Herencia\
3.  Polimorfismo\
4.  Abstracción

------------------------------------------------------------------------

# 1. Encapsulación

La **encapsulación** consiste en **proteger los datos internos de una
clase** y permitir el acceso a ellos únicamente a través de métodos
controlados.

De esta manera se evita que otras partes del programa modifiquen los
datos de forma incorrecta.

## Ejemplo en Java

``` java
public class Persona {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
```

### Explicación

-   `private` evita el acceso directo al atributo.
-   `getNombre()` permite leer el valor.
-   `setNombre()` permite modificarlo de forma controlada.

### Modificadores de acceso en Java

  Modificador   Acceso
  ------------- -------------------------
  private       Solo dentro de la clase
  protected     Clase y subclases
  public        Desde cualquier lugar
  package       Solo dentro del paquete

El objeto **protege sus datos** y solo permite acceso mediante métodos.

------------------------------------------------------------------------

# 2. Herencia

La **herencia** permite crear una clase nueva basada en otra existente.

Esto permite **reutilizar código** y establecer relaciones entre clases.

## Ejemplo

``` java
class Vehiculo {
    String marca;
}

class Auto extends Vehiculo {
    int puertas;
}
```

### Explicación

En este caso:

-   `Vehiculo` es la **clase padre**
-   `Auto` es la **clase hija**

La clase `Auto` **hereda los atributos y métodos** de `Vehiculo`.

### Ventajas

-   Reutilización de código
-   Organización jerárquica
-   Facilita el mantenimiento

Una clase hija **hereda características de la clase padre**.

------------------------------------------------------------------------

# 3. Polimorfismo

El **polimorfismo** significa **"muchas formas"**.

Permite que **un mismo método tenga comportamientos diferentes
dependiendo del objeto que lo utilice**.

## Ejemplo

``` java
class Animal {

    void hacerSonido() {
        System.out.println("Sonido generico");
    }

}

class Perro extends Animal {

    void hacerSonido() {
        System.out.println("Ladrido");
    }

}

class Gato extends Animal {

    void hacerSonido() {
        System.out.println("Maullido");
    }

}
```

## Uso

``` java
Animal a = new Perro();
a.hacerSonido();
```

Salida:

    Ladrido

El **mismo método puede comportarse de diferentes maneras**.

------------------------------------------------------------------------

# 4. Abstracción

La **abstracción** consiste en **mostrar solo la información relevante y
ocultar la complejidad interna**.

Un ejemplo cotidiano es el uso de una **aplicación bancaria**.

El usuario solo ve:

-   Transferir dinero
-   Consultar saldo

Pero no ve toda la lógica interna del sistema.

## Implementación en Java

La abstracción se implementa mediante:

-   **Clases abstractas**
-   **Interfaces**

### Ejemplo

``` java
abstract class Pago {

    abstract void procesar();

}
```

Las clases hijas deberán implementar el método `procesar()`.

Se define **qué debe hacerse**, pero no **cómo se hace**.

------------------------------------------------------------------------

# Resumen de los 4 pilares

  Pilar           Función
  --------------- -----------------------------------------
  Encapsulación   Protege los datos
  Herencia        Permite reutilizar código
  Polimorfismo    Un método con múltiples comportamientos
  Abstracción     Oculta la complejidad

------------------------------------------------------------------------

# Preguntas de reflexion

1.  ¿Qué problema resuelve la encapsulación?
    * La encapsulación resuelve para que algunas partes del programa no modifiquen datos de manera incorrecta.
2.  ¿Cuál es la diferencia entre clase padre y clase hija?
    * La clase hija, a diferencia de la otra, hereda los atributos del padre.
3.  ¿Qué significa polimorfismo?
    * El polimorfismo significa "muchas formas". En programación permite que un mismo método tenga distintos comportamientos en un objeto.
4.  ¿Qué ventaja tiene la abstracción?
    * La principal ventaja que tiene la abstracción es que no se ve la complejidad interna del sistema. Solo ve la información relevante. 
5.  ¿En qué casos usarías herencia en un sistema real?
    * Por ejemplo, usaría herencia en un programa donde debo guardar los datos de un vehículo. Auto y Moto heredan de una clase Vehículo, que tiene los atributos motor, frenos, luces, etc.

------------------------------------------------------------------------

# Actividad práctica

Diseñar un sistema simple con las siguientes clases:

-   `Vehiculo`
-   `Auto`
-   `Moto`

Requisitos:

-   Usar **herencia**
-   Implementar **polimorfismo** en un método `arrancar()`
-   Encapsular los atributos
-   Crear una clase principal que pruebe los objetos

------------------------------------------------------------------------

``` java
public abstract class Vehiculo { //Clase abstracta, las demas clases heredan de el. No crea objetos.
    protected String motor;
    protected Integer frenos;
    
    public Vehiculo(String motor, Integer frenos) {
        this.motor = motor;
        this.frenos = frenos;
    }
    public abstract void arrancar();    
}
```

``` java
public class Auto extends Vehiculo {
    private String modelo;

    public Auto(String motor, Integer frenos, String modelo) {
        super(motor, frenos);
        this.motor = motor;
        this.frenos = frenos;
        this.modelo = modelo;
    } 

    @Override
    public void arrancar() {
        System.out.println("El auto " + modelo + " tiene motor " + motor + " con " + frenos + " frenos");    
    }
}
```

``` java
public class Moto extends Vehiculo {
    private Integer cilindrada;

    public Moto(String motor, Integer frenos, Integer cilindrada) {
        super(motor, frenos);
        this.motor = motor;
        this.frenos = frenos;
        this.cilindrada = cilindrada;
    } 

    @Override
    public void arrancar() {
        System.out.println("La moto con motor " + motor + " arranca con patada. La moto tiene " + frenos + " frenos y una cilindrada de " + cilindrada);    
    }
}
```

``` java
public class App {
    public static void main(String[] args) throws Exception {
        Vehiculo moto = new Moto("1.8 TURBO", 2, 1000);
        moto.arrancar();

        Vehiculo auto = new Auto("V8 BITURBO", 4, "Fiat 600");
        auto.arrancar();
    }
}
```
