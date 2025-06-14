package com.simod.api.rest;

import com.simod.config.URIUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.simod.config.Parametros.TENANT_ID_IN;
import static com.simod.config.Parametros.TENANT_MEMBER;

@RestController
@RequestMapping("/api/find")
@RequiredArgsConstructor
public class FindController {

    private final URIUtil uriUtil;
    private final RestTemplate restTemplate;

    @GetMapping("/v1/**")
    public ResponseEntity<?> lista(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Tenant-Id") String tenantId,
            HttpServletRequest request) {

        return restTemplate.exchange(
                uriUtil.gerarUrl(request, TENANT_ID_IN),
                HttpMethod.GET,
                uriUtil.entity(authorization, tenantId),
                List.class);
    }

    @GetMapping("/user/v1/**")
    public ResponseEntity<?> find(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Tenant-Id") String tenantId,
            HttpServletRequest request) {
        return restTemplate.exchange(
                uriUtil.gerarUrl(request, TENANT_MEMBER, true),
                HttpMethod.GET,
                uriUtil.entity(authorization, tenantId),
                List.class);
    }

    @GetMapping("/unit/v1/**")
    public ResponseEntity<?> findUnit(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Tenant-Id") String tenantId,
            HttpServletRequest request) {
        return restTemplate.exchange(
                uriUtil.gerarUrl(request, TENANT_MEMBER, true),
                HttpMethod.GET,
                uriUtil.entity(authorization, tenantId),
                Object.class);
    }
}
