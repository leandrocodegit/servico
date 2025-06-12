package com.simod.api.integracao;

import com.simod.api.integracao.request.UserQueryRequest;
import feign.Param;
import feign.QueryMap;
import jakarta.ws.rs.QueryParam;
import org.camunda.bpm.engine.rest.dto.identity.TenantDto;
import org.camunda.bpm.engine.rest.dto.identity.UserDto;
import org.camunda.bpm.engine.rest.dto.identity.UserProfileDto;
import org.camunda.bpm.engine.rest.dto.identity.UserQueryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "user-rest", url = "${engine.url}", path = "user")
public interface UserRestCamunda {

    @GetMapping
    List<UserProfileDto> findAll(
            @RequestParam("memberOfTenant") String tenantId,
            @RequestParam(name = "id", required = false ) String userId,
            @RequestParam(name = "memberOfGroup", required = false) String groupId);

    @GetMapping("/{userId}/profile")
    UserProfileDto find(
            @PathVariable("id") String userId);

    @PostMapping("/create")
    void create(
            @RequestBody UserDto dto);

    @DeleteMapping("/{id}")
    void delete(
            @PathVariable("id") String userId);

    @PutMapping("/{id}")
    void atualizar(
            @PathVariable("id") String userId,
            @RequestBody UserProfileDto dto);

}