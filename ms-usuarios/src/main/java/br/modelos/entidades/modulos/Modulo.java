package br.modelos.entidades.modulos;

import br.modelos.entidades.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "modelos")
@Getter
@Setter
public class Modulo {
    @Id
    private UUID id;
    @DBRef
    private Cliente cliente;
    private String nome;
    private String descricaoCurta;
    private String descricaoDetalhada;
    private String versao;
    private boolean ativo;
    @DBRef
    private Categoria categoria;
    @DBRef
    private Menu menu;
}
