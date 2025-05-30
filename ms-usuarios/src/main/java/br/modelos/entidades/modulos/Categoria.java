package br.modelos.entidades.modulos;

import br.modelos.entidades.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "categoria_modulos")
@Getter
@Setter
public class Categoria {

    @Id
    private UUID id;
    @DBRef
    private Cliente cliente;
    private String nome;
}
