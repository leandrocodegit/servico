package br.com.usuarios.keycloak.models;

import lombok.*;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class UserSession {

    private String id;
    private String username;
    private String userId;
    private String ipAddress;
    private Date start;
    private Date lastAccess;
    private boolean rememberMe;
    private Map<String, String> clients;
}
