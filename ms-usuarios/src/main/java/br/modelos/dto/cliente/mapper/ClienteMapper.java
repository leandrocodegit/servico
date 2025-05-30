package br.modelos.dto.cliente.mapper;

import br.modelos.dto.cliente.request.ClienteRequest;
import br.modelos.dto.cliente.response.ClienteResponse;
import br.modelos.entidades.cliente.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponse toResponse(Cliente entity);
    Cliente toEntity(ClienteRequest request);
}
