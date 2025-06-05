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
public class ControleAcesso {

    private Boolean ativo;
    private Boolean controlarDias;
    private Boolean controlarHorario;
    private List<DiasSemana> dias;
    private String inicio;
    private String fim;
    private Long tolerancia;
}
