package br.com.usuarios.keycloak.models;

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
}
