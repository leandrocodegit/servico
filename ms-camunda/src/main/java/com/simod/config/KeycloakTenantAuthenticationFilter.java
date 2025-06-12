package com.simod.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class KeycloakTenantAuthenticationFilter extends OncePerRequestFilter {

    private final ProcessEngine processEngine;

    public KeycloakTenantAuthenticationFilter(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();

            String username = jwt.getClaimAsString("preferred_username");
            String tenantId = jwt.getClaimAsString("azp");
            List<String> roles = jwt.getClaimAsStringList("grupos");
            IdentityService identityService = processEngine.getIdentityService();

           identityService.setAuthentication(username, roles, List.of(tenantId));
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            processEngine.getIdentityService().clearAuthentication();
        }
    }

    private List<String> extractRoles(Jwt jwt) {
        List<String> roles = new ArrayList<>();

        // Realm roles
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.get("roles") instanceof List<?> realmRoles) {
            for (Object role : realmRoles) {
                roles.add(role.toString());
            }
        }

        // Resource access roles (opcional)
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            for (Map.Entry<String, Object> entry : resourceAccess.entrySet()) {
                Map<String, Object> clientRoles = (Map<String, Object>) entry.getValue();
                if (clientRoles.get("roles") instanceof List<?> resRoles) {
                    for (Object role : resRoles) {
                        roles.add(role.toString());
                    }
                }
            }
        }

        return roles;
    }
}
