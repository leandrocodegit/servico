package com.simod.api.integracao.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryRequest {

    protected String id;
    protected String memberOfGroup;
    protected String tenantId;
}
