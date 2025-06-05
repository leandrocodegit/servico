package br.com.usuarios.keycloak.models;

import java.util.List;

public enum GruposName {

    ADM("Administrador", List.of("query-users","view-users","manage-users")),
    AUS("Administrar usuários", List.of("query-users","view-users", "manage-users")),
    EUS("Editar usuários", List.of("query-users","view-users")),
    RUS("Remover usuários", List.of("query-users","view-users")),
    CUS("Cria usuários", List.of("query-users","view-users"));

    private String nome;
    private List<String> roles;

    public String value(){
        return nome;
    }

    public List<String> getRoles() {
        return roles;
    }

    GruposName(String nome, List<String> roles) {
        this.nome = nome;
        this.roles = roles;
    }
}
