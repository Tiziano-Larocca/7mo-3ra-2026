package com.api.gym.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@Builder
public class Profesor extends Persona implements Tareas{
    private String turno;
    
    @Override
    public void anotarAtleta() {
        
    }

    @Override
    public void darDeBaja() {
        
    }
}
