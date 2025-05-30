package br.modelos.dto.cliente.response;

import br.modelos.entidades.cliente.StatusCliente;
import br.modelos.entidades.cliente.TipoPessoa;
import br.modelos.entidades.share.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ClienteResponse {

    private UUID id;
    private String hostName;
    private String nome;
    private String email;
    private String documento;
    private StatusCliente status;
    private TipoPessoa tipoPessoa;
    private Endereco endereco;
    private List<UUID> modulos;
}
