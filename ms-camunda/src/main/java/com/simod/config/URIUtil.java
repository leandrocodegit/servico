package com.simod.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simod.api.rest.ProcessDto;
import com.simod.api.rest.RequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
public class URIUtil {

    @Value("${engine.url}")
    private String engileUrl;
    @Autowired
    private ObjectMapper objectMapper;


    public URI gerarUrl(HttpServletRequest request, String paramTenatn) {
        return gerarUrl(request, paramTenatn, false);
    }

    public URI gerarUrl(HttpServletRequest request, String paramTenatn, boolean isAssignee) {
        var path = request.getRequestURI().substring(request.getRequestURI().indexOf("v1") + 2);

        var tenantId = request.getHeader("X-Tenant-Id");
        var userId = request.getHeader("X-User-Id");


        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            for (String value : entry.getValue()) {
                if (!(entry.getKey().equals("tenantIdIn") && entry.getKey().equals("assignee")))
                    queryParams.add(entry.getKey(), value);
            }
        }

        if (isAssignee)
            queryParams.add("assignee", userId);
        if (path.contains("/group")){
            queryParams.add("type", "DEPARTMENT");
            queryParams.add(Parametros.TENANT_MEMBER, tenantId);
        }else{
            queryParams.add(paramTenatn, tenantId);
        }


        return UriComponentsBuilder.fromHttpUrl(engileUrl + path)
                .queryParams(queryParams)
                .build(true)
                .toUri();
    }

    public HttpHeaders headers(String authorization, String tenantId, String entityId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        headers.set("X-Tenant-Id", tenantId);
        headers.set("tenantId", tenantId);
        headers.set("id", entityId);
        return headers;
    }

    public HttpEntity entity(String authorization, String tenantId) {
        return entity(authorization, tenantId, null);
    }
    public HttpEntity entity(String authorization, String tenantId, String entityId) {
        HttpEntity<Object> entity = new HttpEntity<>(headers(authorization, tenantId, entityId));
        return entity;
    }

    public HttpEntity entityPost(HttpServletRequest request, RequestDto dto, String authorization, String tenantId) {
        return entityPost(parse(request, dto, tenantId), authorization, tenantId, dto.getId());
    }
    public HttpEntity entityPost(Object dto, String authorization, String tenantId,  String entityId) {
        HttpEntity<Object> entity = new HttpEntity<>(dto, headers(authorization, tenantId, entityId));
        return entity;
    }

    public Object parse(HttpServletRequest request, RequestDto dto, String tenantId){
        if(request.getRequestURI().contains("/process-definition")){
            var map = objectMapper.convertValue(dto.getDto(), ProcessDto.class);
            map.setTenantId(tenantId);
            return map;
        }
        return dto;
    }
}
