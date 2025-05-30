package br.modelos.dto.cliente.request;

import br.modelos.entidades.cliente.StatusCliente;
import br.modelos.entidades.cliente.TipoPessoa;
import br.modelos.entidades.share.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ClienteRequest {

    private UUID id;
    private String hostName;
    private String nome;
    private String email;
    private String documento;
    private TipoPessoa tipoPessoa;
    private Endereco endereco;
    private List<UUID> modulos;
}
