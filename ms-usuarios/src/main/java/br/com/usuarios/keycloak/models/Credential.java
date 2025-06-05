package br.com.usuarios.keycloak.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    private String id;
    private boolean temporary;
    private String type;
    private String value;
}
