package com.simod.config;

import com.simod.Exception.ExceptionResponse;
import feign.Request;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.lang.invoke.MethodType;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestFilter implements Filter {

    private final ProcessEngine processEngine;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        var path = ((HttpServletRequest) request).getRequestURI();
        if (!(path.startsWith("/engine-rest"))) {
            chain.doFilter(request, response);
        } else {
            try {
                var token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
                var tenantId = httpRequest.getHeader("X-Tenant-Id");

                String issuerUri = "http://localhost:8080/realms/" + tenantId;

                JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuerUri);
                Jwt jwt = jwtDecoder.decode(token);

                String username = jwt.getClaimAsString("preferred_username");
                List<String> roles = jwt.getClaimAsStringList("grupos");
                IdentityService identityService = processEngine.getIdentityService();

                identityService.setAuthentication(username, roles, List.of(tenantId));

                chain.doFilter(request, response);
            } catch (JwtValidationException exception) {
                throw new ExceptionResponse("Acesso negado");
            } catch (BadJwtException exception) {
                throw new ExceptionResponse("Acesso negado");
            } catch (Exception exception) {
                throw new ExceptionResponse("Erro ao processar requisição");
            } finally {
                processEngine.getIdentityService().clearAuthentication();
            }
        }
    }
}
