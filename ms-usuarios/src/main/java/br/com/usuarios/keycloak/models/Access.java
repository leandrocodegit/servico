package br.com.usuarios.keycloak.models;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Access {

    private boolean view;
    private boolean viewMembers;
    private boolean manageMembers;
    private boolean manage;
    private boolean manageMembership;
}
