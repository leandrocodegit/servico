package com.simod.api.integracao;

import com.simod.api.integracao.request.GrupoQueryRequest;
import jakarta.ws.rs.DELETE;
import org.camunda.bpm.engine.rest.dto.CountResultDto;
import org.camunda.bpm.engine.rest.dto.identity.TenantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "tenant-rest", url = "${engine.url}", path = "tenant")
public interface TenantRestCamunda {

    @GetMapping("/{id}")
    TenantDto getTenant(
            @PathVariable("id") String id);

    @PostMapping("/create")
    void create(
            @RequestBody TenantDto dto);

    @DeleteMapping("/{id}/group-members/{groupId}")
    void deleteGroup(
            @PathVariable("id") String id,
            @PathVariable("groupId") String groupId);

    @PutMapping("/{id}/group-members/{groupId}")
    void createGroup(
            @PathVariable("id") String id,
            @PathVariable("groupId") String groupId);

    @DeleteMapping("/{id}/user-members/{userId}")
    void deleteUser(
            @PathVariable("id") String id,
            @PathVariable("groupId") String userId);

    @PutMapping("/{id}/user-members/{userId}")
    void createUser(
            @PathVariable("id") String id,
            @PathVariable("userId") String userId);

    @PutMapping("/{id}")
    void atualizar(
            @PathVariable("id") String tenantId,
            @RequestBody TenantDto dto);

}