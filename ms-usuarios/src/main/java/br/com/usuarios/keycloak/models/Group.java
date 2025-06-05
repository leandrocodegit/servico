package br.com.usuarios.keycloak.models;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private UUID id;
    private String name;
}
