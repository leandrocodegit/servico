package br.modelos.dto.agenda.response;

import br.modelos.entidades.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public class HorarioResponse {

    private UUID id;
    private LocalDateTime data;
    private Usuario usuario;
}
