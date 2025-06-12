package com.simod.api.rest;

import com.simod.api.integracao.ExternalApiService;
import jakarta.servlet.http.HttpServletRequest;
import org.camunda.bpm.engine.rest.dto.identity.GroupDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/generic/task")
public class GenericTaskController {

    @Autowired
    private ExternalApiService externalApiService;



    @GetMapping("/**")
    public ResponseEntity<?> lista(
            @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
        var list = externalApiService.findAll(path.replace("/api/generic","") + "?memberOfTenant=" + tenantId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find/**")
    public ResponseEntity<?> find(
            @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
        var list = externalApiService.find(path.replace("/api/generic","") + "?memberOfTenant=" + tenantId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/**")
    public ResponseEntity<?> create(@RequestBody TaskDto dto, @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
        dto.setTenantId(tenantId);
        externalApiService.create(path.replace("/api/generic",""), dto);
         return ResponseEntity.ok().build();
    }

    @PutMapping("/**")
    public ResponseEntity<?> update(@RequestBody TaskDto dto, @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
        externalApiService.update(path.replace("/api/generic",""), dto);
         return ResponseEntity.ok().build();
    }

    @DeleteMapping("/**")
    public ResponseEntity<?> remove(@RequestBody TaskDto dto, @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
         externalApiService.delete(String.format("/tenant/%s/group-members/%s", tenantId, dto.getId()));
        return ResponseEntity.ok().build();
    }


}
