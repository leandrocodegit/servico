package br.modelos.dto.agenda.response;

import br.modelos.constantes.DiasSemana;
import br.modelos.constantes.TipoConfiguracaoAgenda;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ConfiguracaoResponse {

    private UUID id;
    private String nome;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dataInicio;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dataFim;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horarioInicio;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horarioFim;
    private TipoConfiguracaoAgenda tipo;
    private int dias;
    private int intervalo;
    private int descanso;
    private boolean ignorarFeriados;
    private boolean ignorarFinalSemana;
    private List<DiasSemana> diasSemana;
}
