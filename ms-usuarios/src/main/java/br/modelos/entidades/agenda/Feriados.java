package br.modelos.entidades.agenda;

import br.modelos.entidades.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "feriados")
@Getter
@Setter
public class Feriados {
    @Id
    private UUID id;
    private LocalDate data;
    private String nome;
}
