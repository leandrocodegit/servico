package br.com.usuarios.controllers;

import br.com.usuarios.keycloak.models.Group;
import br.com.usuarios.keycloak.models.UsuarioKeycloak;
import br.com.usuarios.services.UsuarioService;
import br.modelos.dto.usuarios.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Group>> listaGrupos(@RequestHeader("X-Tenant-ID") String tenantId) {
        return ResponseEntity.ok(usuarioService.litaGrupos(tenantId));
    }

    @PutMapping("/{idUser}/{idGroup}")
    public ResponseEntity<?> joinGrupoUsuarios(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable UUID idUser, @PathVariable UUID idGroup) {
        usuarioService.joinGrupoUsuario(tenantId, idUser, idGroup, false);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idUser}/{idGroup}")
    public ResponseEntity<?> unjoinGrupoUsuarios(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable UUID idUser, @PathVariable UUID idGroup) {
        usuarioService.joinGrupoUsuario(tenantId, idUser, idGroup, true);
        return ResponseEntity.ok().build();
    }
}
