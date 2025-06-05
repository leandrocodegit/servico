package br.com.usuarios.keycloak.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class UsuarioInfo {

    private String id;
    private Map<String, ArrayList> attributes;
    private List<String> requiredActions;
    private boolean emailVerified;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private List<Group> groups;
    private boolean enabled;
    private List<String> tenants;
    private ControleAcesso controle_acesso;
    private Tema tema;

}
