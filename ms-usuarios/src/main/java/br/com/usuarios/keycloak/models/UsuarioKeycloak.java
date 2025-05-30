package br.com.usuarios.keycloak.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class UsuarioKeycloak {

    private String id;
    private Map<String, ArrayList> attributes;
    private List<String> requiredActions;
    private boolean emailVerified;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> groups;
    private boolean enabled;
}
