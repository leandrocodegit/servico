package com.simod.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.rest.dto.identity.GroupQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class GroupCreationTenantInterceptor extends OncePerRequestFilter {

    @Autowired
    private IdentityService identityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if ("POST".equalsIgnoreCase(request.getMethod()) &&
                request.getRequestURI().contains("/engine-rest/group")) {

            // Lê body original
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));


            String tenantId = request.getHeader("X-Tenant-Id");
            // Altera o JSON
            ObjectMapper mapper = new ObjectMapper();


           var filtro = new GroupQueryDto();
           filtro.setMemberOfTenant(tenantId);

            // Converte de volta para string
            String modifiedBody = mapper.writeValueAsString(filtro);

            // Cria request com body modificado
            HttpServletRequest wrappedRequest = new ModifiedBodyRequestWrapper(request, "{\"memberOfTenant\":\"" + tenantId +"\"}");

            // Segue o fluxo com a requisição modificada
            filterChain.doFilter(wrappedRequest, response);
            return;
        }

        // Outros endpoints continuam normais
        filterChain.doFilter(request, response);
    }

}