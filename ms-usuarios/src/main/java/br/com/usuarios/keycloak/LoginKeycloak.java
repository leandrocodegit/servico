package br.com.usuarios.keycloak;

import br.com.usuarios.configs.FeignConfig;
import br.modelos.dto.keycloak.TokenKeycloak;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@FeignClient(value = "keycloak-login", url = "${keycloak-login.url}", configuration = FeignConfig.class)
public interface LoginKeycloak {

    @PostMapping(value = "/master/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ResponseEntity<TokenKeycloak> login(
            @RequestBody Map<String, ?> form);

    @PostMapping(value = "/{realm}/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ResponseEntity<TokenKeycloak> login(
            @PathVariable String realm,
            @RequestBody Map<String, ?> form);

}
