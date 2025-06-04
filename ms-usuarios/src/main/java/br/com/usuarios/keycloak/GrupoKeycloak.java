package br.com.usuarios.keycloak;

import br.com.usuarios.keycloak.models.Group;
import br.com.usuarios.keycloak.models.RoleMappingResponse;
import br.com.usuarios.keycloak.models.UsuarioKeycloak;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "keycloak-groups", url = "${keycloak.url}")
public interface GrupoKeycloak {

    @GetMapping("/{realm}/groups")
    public List<Group> listaGrupos(
            @PathVariable String realm,
            @RequestParam int max);

}
