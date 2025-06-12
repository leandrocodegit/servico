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
@RequestMapping("/api/tenant")
public class TenantController {

    @Autowired
    private GroupRestCamunda groupRestCamunda;
    @Autowired
    private TenantRestCamunda tenantRestCamunda;
    @Autowired
    private UserRestCamunda userRestCamunda;

    @GetMapping
    public ResponseEntity<?> buscar(@RequestHeader("X-Tenant-Id") String tenantId){
        return ResponseEntity.ok( tenantRestCamunda.getTenant(tenantId));
    }

    @GetMapping("/group")
    public ResponseEntity<?> buscarGrupos(@RequestHeader("X-Tenant-Id") String tenantId){
        return ResponseEntity.ok( groupRestCamunda.findAll(tenantId, null, null));
    }

    @PostMapping("/member/group/{id}")
    public ResponseEntity<?> vincularGrupo(@PathVariable("id") String id, @RequestHeader("X-Tenant-Id") String tenantId){
        tenantRestCamunda.createGroup(tenantId, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/member/group/{id}")
    public ResponseEntity<?> desvincularGrupo(@PathVariable("id") String id, @RequestHeader("X-Tenant-Id") String tenantId){
        tenantRestCamunda.deleteGroup(tenantId, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/member/user/{id}")
    public ResponseEntity<?> vincularUser(@PathVariable("id") String id, @RequestHeader("X-Tenant-Id") String tenantId){
        tenantRestCamunda.createGroup(tenantId, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/member/user/{id}")
    public ResponseEntity<?> desvincularUser(@PathVariable("id") String id, @RequestHeader("X-Tenant-Id") String tenantId){
        tenantRestCamunda.deleteGroup(tenantId, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<?> desvincularUser(@RequestBody TenantDto tenantDto, @RequestHeader("X-Tenant-Id") String tenantId){
        var tenant =  tenantRestCamunda.getTenant(tenantId);
        tenant.setName(tenantDto.getName());
        tenantRestCamunda.atualizar(tenantId, tenant);
        return ResponseEntity.ok().build();
    }


}
