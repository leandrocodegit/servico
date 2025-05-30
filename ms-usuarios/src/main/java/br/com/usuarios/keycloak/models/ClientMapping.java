package br.com.usuarios.keycloak.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientMapping {

    private String id;
    private String client;
    private List<RoleRepresentation> mappings;
}
