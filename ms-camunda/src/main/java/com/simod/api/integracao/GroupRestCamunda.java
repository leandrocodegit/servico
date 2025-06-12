package com.simod.api.integracao;

import com.simod.api.integracao.request.GrupoQueryRequest;
import org.camunda.bpm.engine.rest.dto.CountResultDto;
import org.camunda.bpm.engine.rest.dto.identity.GroupDto;
import org.camunda.bpm.engine.rest.dto.identity.UserProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "group-rest", url = "${engine.url}", path = "group")
public interface GroupRestCamunda {

    @GetMapping("/{groupId}")
    GroupDto find(
            @PathVariable("groupId") String groupId);

    @GetMapping
    List<GroupDto> findAll(
            @RequestParam("memberOfTenant") String tenantId,
            @RequestParam(name = "id", required = false ) String groupId,
            @RequestParam(name = "member", required = false) String userId);

    @PostMapping("/create")
    void create(
            @RequestBody GroupDto groupDto);

    @DeleteMapping("/{id}")
    void delete(
            @PathVariable("id") String id);

    @PutMapping("/{groupId}")
    void atualizar(
            @PathVariable("groupId") String groupId,
            @RequestBody GroupDto groupDto);

    @DeleteMapping("/{id}/members/{userId}")
    void deleteMember(
            @PathVariable("id") String id,
            @PathVariable("userId") String userId);

    @PutMapping("/{id}/members/{userId}")
    void createMember(
            @PathVariable("id") String id,
            @PathVariable("userId") String userId);

}