package br.modelos.dto.usuarios.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UsuarioKeycloakResponse {

    private String id;
    private String email;
    private String firstName;
    private boolean enabled;
    private Map<String, Object> attributes;
    private Long createdTimestamp;
    private boolean emailVerified;
}
