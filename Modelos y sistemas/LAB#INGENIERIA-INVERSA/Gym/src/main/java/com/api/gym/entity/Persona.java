package com.api.gym.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected int dni;
}