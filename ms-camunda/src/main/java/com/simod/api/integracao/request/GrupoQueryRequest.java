package com.simod.api.integracao.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoQueryRequest {

    protected String id;
    protected String[] ids;
    protected String name;
    protected String nameLike;
    protected String type;
    protected String member;
    protected String tenantId;


}
