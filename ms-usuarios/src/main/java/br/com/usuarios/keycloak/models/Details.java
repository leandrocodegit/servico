package br.com.usuarios.keycloak.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Details {

    private String token_id;
    private String grant_type;
    private String refresh_token_type;
    private String scope;
    private String refresh_token_id;
    private String code_id;
    private String client_auth_method;
}
