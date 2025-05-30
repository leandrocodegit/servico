package br.modelos.dto.agenda.request;

import br.modelos.constantes.DiasSemana;
import br.modelos.constantes.TipoConfiguracaoAgenda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ConfiguracaoRequest {

    private UUID id;
    private String nome;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private TipoConfiguracaoAgenda tipo;
    private int dias;
    private int intervalo;
    private int descanso;
    private boolean ignorarFeriados;
    private boolean ignorarFinalSemana;
    private List<DiasSemana> diasSemana;
}
