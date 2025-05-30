package br.modelos.dto.usuarios.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UsuarioKeycloakRequest {

    private String firstName;
    private String email;
    private String username;
    private boolean enabled;
    private List<Credencial> credentials;
    private Map<String, Object> attributes;
}
