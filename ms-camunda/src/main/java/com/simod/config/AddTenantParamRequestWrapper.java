package com.simod.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

public class AddTenantParamRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String[]> customParams = new HashMap<>();

    public AddTenantParamRequestWrapper(HttpServletRequest request) {
        super(request);
        customParams.putAll(request.getParameterMap());

        var contem = customParams.get("memberOfTenant");
         String tenantId = request.getHeader("X-Tenant-Id");


        customParams.put("memberOfTenant", new String[]{tenantId});
    }

    @Override
    public String getParameter(String name) {
        String[] values = customParams.get(name);
        return (values != null && values.length > 0) ? values[0] : null;
    }

    @Override
    public String[] getParameterValues(String name) {
        return customParams.get(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return customParams;
    }
}
