package br.com.usuarios.keycloak;

import br.com.usuarios.configs.FeignConfig;
import br.com.usuarios.keycloak.models.*;
import br.modelos.dto.usuarios.request.UsuarioKeycloakRequest;
import br.modelos.dto.usuarios.request.UsuarioRequest;
import br.modelos.dto.usuarios.response.UsuarioResponse;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "keycloak", url = "${keycloak.url}")
public interface UserKeycloak {

    @GetMapping("/{realm}/users/{id}")
    public UsuarioKeycloak buscarUsuarioPorId(
            @PathVariable String realm,
            @PathVariable UUID id);

    @GetMapping("/{realm}/users")
    public List<UsuarioKeycloak> listaUsuarios(
            @PathVariable String realm,
            @RequestParam boolean briefRepresentation,
            @RequestParam int first,
            @RequestParam int max);

    @GetMapping("/{realm}/users/{id}/role-mappings")
    public RoleMappingResponse buscarRolesUser(
            @PathVariable String realm,
            @RequestParam UUID id);

    @PutMapping("/{realm}/users/{id}")
    public UsuarioKeycloak atualizarUsuario(
            @PathVariable String realm,
            @PathVariable UUID id,
            @RequestBody UsuarioKeycloak request);

    @PutMapping("/{realm}/users/{idUser}/groups/{idGroup}")
    public UsuarioKeycloak joinGrupoUsuario(
            @PathVariable String realm,
            @PathVariable UUID idUser,
            @PathVariable UUID idGroup);

    @DeleteMapping("/{realm}/users/{idUser}/groups/{idGroup}")
    public UsuarioKeycloak unjoinGrupoUsuario(
            @PathVariable String realm,
            @PathVariable UUID idUser,
            @PathVariable UUID idGroup);

    @GetMapping("/{realm}/users/{idUser}/groups")
    public List<Group> listaGruposUsuario(
            @PathVariable String realm,
            @PathVariable UUID idUser,
            @RequestParam int max);

    @PostMapping("/{realm}/users")
    public void criarUsuario(
            @PathVariable String realm,
            UsuarioKeycloak usuarioKeycloak);

    @GetMapping("/{realm}/users")
    public List<UsuarioRequest> buscarUsuario(
            @PathVariable String realm,
            @RequestParam(required = true) String email);

    @PutMapping("/{realm}/users/{idUser}/reset-password")
    public void criarCredencial(
            @RequestBody Credential credential,
            @PathVariable String realm,
            @PathVariable UUID idUser);

    @GetMapping("/{realm}/users/{idUser}/credentials")
    public List<Credential> listaCredencial(
            @PathVariable String realm,
            @PathVariable UUID idUser);

    @GetMapping("/{realm}/users/{idUser}/groups")
    public List<Group> listaGruposUsuario(
            @PathVariable String realm,
            @PathVariable String idUser,
            @RequestParam int max);

    @DeleteMapping(value = "/{realm}/users/{idUser}")
    void removerUsuario(
            @PathVariable String realm,
            @PathVariable UUID idUser);

    @PostMapping(value = "/{realm}/users/{idUser}/logout")
    void logout(
            @PathVariable String realm,
            @PathVariable UUID idUser);
    @GetMapping(value = "/{realm}/users/{idUser}/sessions")
    List<UserSession> sessionsUsuario(
            @PathVariable String realm,
            @PathVariable UUID idUser);


}
