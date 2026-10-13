import com.api.gym.entity.Profesor;

public class Gym {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        Profesor profesor = Profesor.builder()
        .nombre("miqueas")
        .apellido("paz")
        .dni(1234)
        .edad(60)
        .turno("mañana")
        .build();
        
        System.out.println(profesor.getApellido());
    }
}
