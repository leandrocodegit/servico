package br.com.usuarios.keycloak.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UsuarioKeycloakRequest {

    private UUID id;
    private UUID sub;
    private Map<String, Object> attributes;
    private List<String> requiredActions;
    private boolean emailVerified;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<Group> groups;
    private boolean enabled;
    private List<String> acessos;
    private RoleMappingResponse roles;
    private Access access;
    @JsonProperty("controle_acesso")
    private ControleAcesso controleAcesso;
    private Tema tema;
}
