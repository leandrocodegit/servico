package br.modelos.dto.modulos.mapper;

import br.modelos.dto.modulos.request.ModuloRequest;
import br.modelos.dto.modulos.response.ModuloResponse;
import br.modelos.entidades.modulos.Modulo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuloMapper {

    ModuloResponse toResponse(Modulo entity);
    Modulo toEntity(ModuloRequest request);
}
