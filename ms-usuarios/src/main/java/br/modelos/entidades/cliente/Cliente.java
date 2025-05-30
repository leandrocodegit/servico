package br.modelos.entidades.cliente;

import br.modelos.entidades.share.Endereco;
import br.modelos.entidades.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "clientes")
@Getter
@Setter
public class Cliente {
    @Id
    private UUID id;
    private String hostName;
    private String nome;
    private String email;
    private String documento;
    private StatusCliente status;
    private TipoPessoa tipoPessoa;
    private Endereco endereco;
    private List<UUID> modulos;

    public static Cliente normalize(Cliente cliente) {
        if (cliente.getId() == null)
            cliente.setId(UUID.randomUUID());
        return cliente;
    }
}
