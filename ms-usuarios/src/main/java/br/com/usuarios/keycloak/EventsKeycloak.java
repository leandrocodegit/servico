package br.com.usuarios.keycloak;

import br.com.usuarios.keycloak.models.AuthEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "keycloak-events", url = "${keycloak.url}")
public interface EventsKeycloak {

    @GetMapping("/{realm}/events?user")
    public List<AuthEvent> listaEventoUsuario(
            @PathVariable String realm,
            @RequestParam("user") String idUser,
            @RequestParam("first") int first,
            @RequestParam("max") int max);
}
