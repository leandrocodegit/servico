package br.com.usuarios.integracao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TurnStyleResponse {

    private boolean success;
    private String hostname;
}
