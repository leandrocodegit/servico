package br.modelos.dto.agenda.mapper;

import br.modelos.dto.agenda.request.AgendaRequest;
import br.modelos.dto.agenda.response.AgendaResponse;
import br.modelos.entidades.agenda.Agenda;
import br.modelos.entidades.modulos.Modulo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

    AgendaResponse toResponse(Agenda entity);
    Agenda toEntity(AgendaRequest request);
}
