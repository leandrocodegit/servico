package com.simod.config;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomGroupService {

    @Autowired
    private IdentityService identityService;

    public void criarGrupoComTenant(String nomeGrupo, String tenantId) {
        Group grupo = identityService.newGroup(nomeGrupo);
        grupo.setName(nomeGrupo);
        identityService.saveGroup(grupo);
        identityService.createTenantGroupMembership(tenantId, grupo.getId());
    }
}
