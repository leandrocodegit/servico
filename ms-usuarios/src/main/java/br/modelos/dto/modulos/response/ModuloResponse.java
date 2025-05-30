package br.modelos.dto.modulos.response;

import br.modelos.constantes.TipoConfiguracaoAgenda;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class ModuloResponse {

    private UUID id;
    private TipoConfiguracaoAgenda tipo;
    private String nome;
    private String descricaoCurta;
    private String descricaoDetalhada;
    private String versao;
    private boolean ativo;
    private MenuResponse menu;
}
