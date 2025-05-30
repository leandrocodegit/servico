package br.modelos.dto.agenda.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class AgendaRequest {

    private UUID id;
    private LocalDate data;
    private ConfiguracaoRequest configuracao;
    private List<HorarioRequest> horarios;
}
