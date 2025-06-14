package com.simod.api.integracao.endpoint;

 import org.springframework.cloud.openfeign.FeignClient;
 import org.springframework.http.MediaType;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestHeader;
 import org.springframework.web.bind.annotation.RequestPart;
 import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "deployment", url = "${engine.url}")
public interface DeploymentRest {

    @PostMapping(value = "/deployment/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> upload(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Tenant-Id") String tenantId,
            @RequestPart("tenant-id") String tenant,
            @RequestPart("deployment-source") String source,
            @RequestPart("deploy-changed-only") boolean changedOnly,
            @RequestPart("enable-duplicate-filtering") boolean duplicateFiltering,
            @RequestPart("deployment-name") String name,
            @RequestPart("deployment-activation-time") String time,
            @RequestPart("data") MultipartFile data,
            @RequestPart(value = "form", required = false) MultipartFile form);
}
