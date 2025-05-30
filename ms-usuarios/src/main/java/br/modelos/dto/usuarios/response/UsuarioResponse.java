package br.modelos.dto.usuarios.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class UsuarioResponse {

    private String id;
    private String email;
    private String firstName;
    private boolean enabled;
    private Map<String, Object> attributes;
}
