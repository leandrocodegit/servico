package br.modelos.dto.modulos.request;

import br.modelos.constantes.TipoConfiguracaoAgenda;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class ModuloRequest {

    private UUID id;
    private TipoConfiguracaoAgenda tipo;
    private String nome;
    private String descricaoCurta;
    private String descricaoDetalhada;
    private String versao;
    private boolean ativo;
    private MenuRequest menu;
}
