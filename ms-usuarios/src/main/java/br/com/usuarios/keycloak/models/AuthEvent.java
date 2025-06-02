package br.com.usuarios.keycloak.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthEvent {

    private Date time;
    private String type;
    private String realmId;
    private String clientId;
    private String userId;
    private String sessionId;
    private String ipAddress;
    private Details details;
}
