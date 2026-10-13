package com.api.gym.entity;

import java.util.List;
import lombok.*;

@Data
public class Gimnasio {
    private String nombre;
    private Membresia membresia;
    private List<Profesor> profesores;
}
