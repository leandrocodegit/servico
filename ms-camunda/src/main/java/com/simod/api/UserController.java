package com.simod.api;

import com.simod.api.integracao.GroupRestCamunda;
import com.simod.api.integracao.TenantRestCamunda;
import com.simod.api.integracao.UserRestCamunda;
import com.simod.api.integracao.request.UserQueryRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.rest.dto.CountResultDto;
import org.camunda.bpm.engine.rest.dto.identity.TenantDto;
import org.camunda.bpm.engine.rest.dto.identity.UserQueryDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.rest.dto.task.TaskQueryDto;
import org.camunda.bpm.engine.rest.sub.task.TaskReportResource;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private GroupRestCamunda groupRestCamunda;
    @Autowired
    private TenantRestCamunda tenantRestCamunda;
    @Autowired
    private UserRestCamunda userRestCamunda;

    @GetMapping()
    public ResponseEntity<?> lista(@RequestHeader("X-Tenant-Id") String tenantId){
        return ResponseEntity.ok(userRestCamunda.findAll(tenantId,null,  null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") String userId, @RequestHeader("X-Tenant-Id") String tenantId){
        var result = userRestCamunda.findAll(tenantId, userId, null);

        if(result.isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.ok(result.stream().findFirst().get());
    }


    @GetMapping("/group/{id}")
    public ResponseEntity<?> listaPorGrupo(@PathVariable("id") String groupId, @RequestHeader("X-Tenant-Id") String tenantId){
        var result = userRestCamunda.findAll(tenantId, null, groupId);

        if(result.isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.ok(result.stream().findFirst().get());
    }

    @GetMapping("/group/member/{id}")
    public ResponseEntity<?> buscarGruposPorMembro(@PathVariable("id") String id, @RequestHeader("X-Tenant-Id") String tenantId) {
        var result = groupRestCamunda.findAll(tenantId, null, id);

        if (result.isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.ok(result.stream().findFirst().get());
    }



}
