package br.com.usuarios.keycloak.models;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleMappingResponse {

    private List<RoleRepresentation> realmMappings;
    private Map<String, ClientMapping> clientMappings;
}
