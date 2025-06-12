package com.simod.api;

import com.simod.api.integracao.GroupRestCamunda;
import com.simod.api.integracao.TenantRestCamunda;
import com.simod.api.integracao.UserRestCamunda;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;
import org.camunda.bpm.engine.rest.dto.identity.GroupDto;
import org.camunda.bpm.engine.rest.dto.identity.GroupQueryDto;
import org.camunda.bpm.engine.rest.dto.identity.TenantDto;
import org.camunda.bpm.engine.rest.util.EngineUtil;
import org.camunda.bpm.engine.rest.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupRestCamunda groupRestCamunda;
    @Autowired
    private TenantRestCamunda tenantRestCamunda;
    @Autowired
    private UserRestCamunda userRestCamunda;

    @GetMapping
    public ResponseEntity<?> lista(@RequestHeader("X-Tenant-Id") String tenantId) {
        return ResponseEntity.ok(groupRestCamunda.findAll(null, tenantId, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") String id, @RequestHeader("X-Tenant-Id") String tenantId) {
        return ResponseEntity.ok(validar(tenantId, id, null));
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<?> buscarGruposPorMembro(@PathVariable("id") String id, @RequestHeader("X-Tenant-Id") String tenantId) {
        var result = groupRestCamunda.findAll(tenantId, id, null);

        if (result.isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.ok(result.stream().findFirst().get());
    }

    @GetMapping("/members/{id}/user")
    public ResponseEntity<?> listaPorGrupo(@PathVariable("id") String groupId, @RequestHeader("X-Tenant-Id") String tenantId) {
        return ResponseEntity.ok( userRestCamunda.findAll(tenantId, null, groupId));
    }

    @PostMapping("/member/{id}/{userId}")
    public ResponseEntity<?> vincularUsuario(@PathVariable("id") String id, @PathVariable("userId") String userId, @RequestHeader("X-Tenant-Id") String tenantId) {
        var result = userRestCamunda.findAll(tenantId, userId, null);
        if (result.isEmpty())
            throw new RuntimeException("Erro de tenant");
        else {
            result = userRestCamunda.findAll(tenantId, userId, id);
            if (!result.isEmpty())
                throw new RuntimeException("Grupo já vinculado");
        }
        validar(tenantId, id, null);
        groupRestCamunda.createMember(id, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/member/{id}/{userId}")
    public ResponseEntity<?> desvincularUsuario(@PathVariable("id") String id, @PathVariable("userId") String userId, @RequestHeader("X-Tenant-Id") String tenantId) {
        validar(tenantId, id, userId);
        groupRestCamunda.deleteMember(id, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<?> criar(@RequestBody GroupDto groupDto, @RequestHeader("X-Tenant-Id") String tenantId) {
        groupRestCamunda.create(groupDto);
        tenantRestCamunda.createGroup(groupDto.getId(), tenantId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") String id, @RequestHeader("X-Tenant-Id") String tenantId) {
        validar(tenantId, id, null);
        groupRestCamunda.delete(id);
        return ResponseEntity.ok().build();
    }

    private GroupDto validar(String tenantId, String id, String userId) {
        var result = groupRestCamunda.findAll(tenantId, id, userId);

        if (result.isEmpty())
            throw new RuntimeException("Erro de tenant");
        return result.stream().findFirst().get();
    }
}
