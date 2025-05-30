package br.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class User {
    private UUID id;
    private String email;
    private String nome;
}
