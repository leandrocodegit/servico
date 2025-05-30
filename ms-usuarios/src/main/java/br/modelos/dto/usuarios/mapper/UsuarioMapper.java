package br.modelos.dto.usuarios.mapper;

import br.modelos.dto.usuarios.request.UsuarioKeycloakRequest;
import br.modelos.dto.usuarios.request.UsuarioRequest;
import br.modelos.dto.usuarios.response.UsuarioResponse;
import br.modelos.entidades.usuarios.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse toResponse(Usuario entity);
    Usuario toEntity(UsuarioRequest request);
    Usuario toEntity(UsuarioResponse request);
    UsuarioKeycloakRequest toKeycloak(UsuarioRequest request);
}
