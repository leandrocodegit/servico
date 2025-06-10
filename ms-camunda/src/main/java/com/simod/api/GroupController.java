package com.simod.api;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.rest.GroupRestService;
import org.camunda.bpm.engine.rest.IdentityRestService;
import org.camunda.bpm.engine.rest.dto.identity.GroupDto;
import org.camunda.bpm.engine.rest.dto.identity.GroupQueryDto;
import org.camunda.bpm.engine.rest.impl.GroupRestServiceImpl;
import org.camunda.bpm.engine.rest.sub.identity.impl.GroupResourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private IdentityService identityService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> listaGrupos(@RequestHeader("X-Tenant-Id") String tenantId){
        List<Group> groups = identityService
                .createGroupQuery()
                .memberOfTenant(tenantId)
                .list();
        return ResponseEntity.ok(groups.stream()
                .map(GroupDto::fromGroup).toList());
    }
}
