package com.simod.api.rest;

import com.simod.api.integracao.endpoint.DeployDto;
import com.simod.api.integracao.endpoint.DeploymentRest;
import com.simod.config.URIUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.IdentityService;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import static com.simod.config.Parametros.TENANT_ID_IN;

@RestController
@RequestMapping("/api/create")
@RequiredArgsConstructor
public class CreateController {

     private final URIUtil uriUtil;
     private final RestTemplate restTemplate;
     private final DeploymentRest deploymentRest;

    private final IdentityService identityService;

    @PostMapping("/v1/**")
    public ResponseEntity<?> criar(
            @RequestBody RequestDto dto,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Tenant-Id") String tenantId,
            HttpServletRequest request) {

        var response = restTemplate.exchange(
                uriUtil.gerarUrl(request, TENANT_ID_IN),
                HttpMethod.POST,
                uriUtil.entityPost(request, dto, authorization, tenantId),
                Object.class);

            if(request.getRequestURI().endsWith("/group/create"))
                identityService.createTenantGroupMembership(tenantId, dto.getId());
        return response;
    }

    @PutMapping("/v1/**")
    public ResponseEntity<?> atualizar(
            @RequestBody RequestDto dto,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Tenant-Id") String tenantId,
            HttpServletRequest request) {

        return restTemplate.exchange(
                uriUtil.gerarUrl(request, TENANT_ID_IN),
                HttpMethod.PUT,
                uriUtil.entityPost(request, dto, authorization, tenantId),
                Object.class);
    }

    @PostMapping("/process/v1/**")
    public ResponseEntity<?> criarProcessInstance(
            @RequestBody RequestDto dto,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Tenant-Id") String tenantId,
            HttpServletRequest request) {
        return restTemplate.exchange(
                uriUtil.gerarUrl(request, TENANT_ID_IN),
                HttpMethod.POST,
                uriUtil.entityPost(request, dto, authorization, tenantId),
                Object.class);
        }

    @PostMapping(value = "/deploy/v1/**", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> criarDeployment(
            @RequestPart("file") MultipartFile data,
            @RequestPart(value = "form", required = false) MultipartFile form,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Tenant-Id") String tenantId,
            DeployDto dto) {

        return deploymentRest.upload(
                authorization,
                tenantId,
                tenantId,
                dto.getSource(),
                dto.isChangedOnly(),
                dto.isDuplicateFiltering(),
                dto.getName(),
                dto.getActivationTime(),
                data,
                form
        );
    }
}
