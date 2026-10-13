package com.api.gym.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@Builder
public class Atleta extends Persona {
    private int peso;
}
