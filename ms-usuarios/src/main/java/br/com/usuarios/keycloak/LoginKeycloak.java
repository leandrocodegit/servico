package br.com.usuarios.keycloak;

import br.com.usuarios.configs.FeignConfig;
import br.modelos.dto.keycloak.TokenKeycloak;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "keycloak-login", url = "${keycloak-login.url}", configuration = FeignConfig.class)
public interface LoginKeycloak {

    @PostMapping(value = "/master/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ResponseEntity<TokenKeycloak> login(@RequestBody Map<String, ?> form);
}
