package br.modelos.entidades.agenda;

import br.modelos.entidades.cliente.Cliente;
import br.modelos.entidades.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "horario_agendas")
@Getter
@Setter
public class Horario {
    @Id
    private UUID id;
    @DBRef
    private Cliente cliente;
    private LocalDateTime data;
    @DBRef
    private Usuario usuario;
}
