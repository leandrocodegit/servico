package br.modelos.dto.agenda.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class AgendaResponse {

    private UUID id;
    private LocalDate data;
    private ConfiguracaoResponse configuracao;
    private List<HorarioResponse> horarios;
}
