package com.simod.api.integracao.endpoint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeployDto {

    private String name;
    private boolean changedOnly;
    private String source;
    private boolean duplicateFiltering;
    private String activationTime;
}
