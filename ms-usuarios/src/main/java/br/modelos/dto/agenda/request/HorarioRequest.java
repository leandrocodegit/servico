package br.modelos.dto.agenda.request;

import br.modelos.entidades.cliente.Cliente;
import br.modelos.entidades.modulos.Menu;
import br.modelos.entidades.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class HorarioRequest {

    private UUID id;
    private LocalDateTime data;
    private Cliente cliente;
    private Usuario usuario;
}
