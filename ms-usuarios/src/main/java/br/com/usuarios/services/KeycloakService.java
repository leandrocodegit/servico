package br.com.usuarios.services;

import br.com.usuarios.keycloak.LoginKeycloak;
import br.modelos.dto.keycloak.TokenKeycloak;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final LoginKeycloak loginKeycloak;


    public TokenKeycloak login() {

        Map<String, String> forms = new HashMap<>();
        forms.put("client_id", "cliente-api");
        forms.put("client_secret","ZGMGTmArvo0iUmzy33wefE2wREA6SYMt");
        forms.put("grant_type","client_credentials");
        forms.put("scope", "openid profile");

        return "";
    }
}
