package br.modelos.entidades.agenda;

import br.modelos.entidades.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document(collection = "agendas")
@Getter
@Setter
public class Agenda {

    @Id
    private UUID id;
    @DBRef
    private Cliente cliente;
    private LocalDate data;
    private Configuracao configuracao;
    @DBRef
    private List<Horario> horarios;
}
