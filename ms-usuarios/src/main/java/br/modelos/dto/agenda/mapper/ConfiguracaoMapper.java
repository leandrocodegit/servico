package br.modelos.dto.agenda.mapper;

import br.modelos.dto.agenda.request.ConfiguracaoRequest;
import br.modelos.dto.agenda.response.ConfiguracaoResponse;
import br.modelos.entidades.agenda.Configuracao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfiguracaoMapper {

    ConfiguracaoResponse toResponse(Configuracao entity);
    Configuracao toEntity(ConfiguracaoRequest request);
}
