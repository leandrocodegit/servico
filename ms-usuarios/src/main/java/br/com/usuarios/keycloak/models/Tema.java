package br.com.usuarios.keycloak.models;

import br.modelos.constantes.DiasSemana;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tema {

    private boolean dark;
    private String color;
}
