package com.simod.api.rest;

import com.simod.api.integracao.ExternalApiService;
import jakarta.servlet.http.HttpServletRequest;
import org.camunda.bpm.engine.rest.dto.identity.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/generic")
public class GenericGroupController {

    @Autowired
    private ExternalApiService externalApiService;



    @GetMapping("/**")
    public ResponseEntity<?> lista(
            @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
        var list = externalApiService.findAll(path.replace("/api/generic/processo","") + "?memberOfTenant=" + tenantId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find/**")
    public ResponseEntity<?> find(
            @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
        var list = externalApiService.find(path.replace("/api/generic/find/processo","") + "?memberOfTenant=" + tenantId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/**")
    public ResponseEntity<?> create(@RequestBody GroupDto dto, @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
        externalApiService.create(path.replace("/api/generic/processo",""), dto);
        externalApiService.update(String.format("/tenant/%s/group-members/%s", tenantId, dto.getId()), null);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/**")
    public ResponseEntity<?> update(@RequestBody GroupDto dto, @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
        externalApiService.update(path.replace("/api/generic/processo",""), dto);
         return ResponseEntity.ok().build();
    }

    @DeleteMapping("/**")
    public ResponseEntity<?> remove(@RequestBody GroupDto dto, @RequestHeader("X-Tenant-Id") String tenantId, HttpServletRequest request) {
        String path = request.getRequestURI();
         externalApiService.delete(String.format("/tenant/%s/group-members/%s", tenantId, dto.getId()));
        return ResponseEntity.ok().build();
    }


}
