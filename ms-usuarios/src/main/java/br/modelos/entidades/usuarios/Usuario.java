package br.modelos.entidades.usuarios;

import br.modelos.constantes.TipoConfiguracaoAgenda;
import br.modelos.entidades.agenda.Configuracao;
import br.modelos.entidades.cliente.Cliente;
import br.modelos.entidades.cliente.Tenant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Document(collection = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    private UUID id;
    @DBRef
    private Tenant tenant;
    private String email;
    private String firstName;
    private boolean enabled;
    private Map<String, Object> attributes;
    private Long createdTimestamp;
    private boolean emailVerified;

    public static Usuario normalize(Usuario usuario) {
        if (usuario.getId() == null)
            usuario.setId(UUID.randomUUID());
        return usuario;
    }
}
