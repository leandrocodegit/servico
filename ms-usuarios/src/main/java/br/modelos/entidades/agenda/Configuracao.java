package br.modelos.entidades.agenda;

import br.modelos.constantes.DiasSemana;
import br.modelos.constantes.TipoConfiguracaoAgenda;
import br.modelos.entidades.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "configuracao-agenda")
@Getter
@Setter
public class Configuracao {

    @Id
    private UUID id;
    @DBRef
    private Cliente cliente;
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

    public static Configuracao normalize(Configuracao configuracao){
        if(configuracao.getId() == null)
            configuracao.setId(UUID.randomUUID());
        if(configuracao.getNome() == null)
            configuracao.setNome("Configuração padrão");
        if(configuracao.getDataInicio() == null)
            configuracao.setDataInicio(LocalDateTime.now());
        if(configuracao.getDataFim() == null)
            configuracao.setDataFim(LocalDateTime.now().plusMonths(1));
        if(configuracao.getHorarioInicio() == null)
            configuracao.setHorarioInicio(LocalTime.now());
        if(configuracao.getHorarioFim() == null)
            configuracao.setHorarioFim(LocalTime.now().plusHours(1));
        if(configuracao.getTipo() == null)
            configuracao.setTipo(TipoConfiguracaoAgenda.P);
        return configuracao;
    }
}
