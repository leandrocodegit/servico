package com.simod.api.integracao.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private String id;
    private String name;
    private String path;
    private int subGroupCount;
    private List<Group> subGroups;
    private Access access;
}
