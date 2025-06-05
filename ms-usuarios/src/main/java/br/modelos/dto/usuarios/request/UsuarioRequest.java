package br.modelos.dto.usuarios.request;

import br.com.usuarios.keycloak.models.ControleAcesso;
import br.com.usuarios.keycloak.models.Group;
import br.com.usuarios.keycloak.models.Tema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRequest {

    private UUID id;
    @NotEmpty(message = "Documento não pode ser vazio")
    private String username;
    @NotEmpty(message = "Nome não pode ser vazio")
    private String firstName;
    @NotEmpty(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;
    private boolean emailVerified;
    private List<Group> groups;
    private ControleAcesso controle_acesso;
    private Tema tema;
}
