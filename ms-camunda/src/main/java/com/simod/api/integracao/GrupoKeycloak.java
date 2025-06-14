package com.simod.api.integracao;

import com.simod.api.integracao.request.GroupRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "keycloak-groups", url = "${keycloak.url}")
public interface GrupoKeycloak {


    @PostMapping("/{realm}/groups")
    public void criarGrupo(
            @PathVariable String realm,
            @RequestBody GroupRequest group);

}
