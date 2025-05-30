package br.modelos.dto.agenda.mapper;

import br.modelos.dto.agenda.request.HorarioRequest;
import br.modelos.dto.agenda.response.HorarioResponse;
import br.modelos.entidades.agenda.Horario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HorarioMapper {

    HorarioResponse toResponse(Horario entity);
    Horario toEntity(HorarioRequest request);
}
