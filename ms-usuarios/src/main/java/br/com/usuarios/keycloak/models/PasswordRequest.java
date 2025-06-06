package br.com.usuarios.keycloak.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PasswordRequest {

    private String old;
    private String username;
    private String password;
    private String confirm;
    private UUID sessionId;

}
