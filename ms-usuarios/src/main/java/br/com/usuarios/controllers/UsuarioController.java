package br.com.usuarios.controllers;

import br.com.usuarios.keycloak.models.*;
import br.com.usuarios.services.UsuarioService;
import br.modelos.dto.usuarios.request.UsuarioRequest;
import br.modelos.dto.usuarios.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioKeycloak>> listaUsuarios(@RequestHeader("X-Tenant-ID") String tenantId, @RequestHeader("X-User-ID") UUID idUserLogado, Pageable pageable) {
        return ResponseEntity.ok(usuarioService.listaTodosUsuarios(tenantId, pageable).stream().filter(user -> !user.getId().equals(idUserLogado.toString())).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioKeycloak> buscarPorId(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorID(tenantId, id));
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UsuarioInfo> buscarInfo(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.buscarInfoUsuarioPorID(tenantId, id));
    }

    @GetMapping("/sessions/{userId}")
    public ResponseEntity<List<UserSession>> sessoesUsuario(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable UUID userId) {
        return ResponseEntity.ok(usuarioService.listaSessoesUsuario(tenantId, userId));
    }

    @PostMapping
    public ResponseEntity<?> criarUsuarios(@RequestHeader("X-Tenant-ID") String tenantId, @RequestBody @Validated UsuarioRequest request) {
        usuarioService.criarUsuario(tenantId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> atualizarUsuarios(@RequestHeader("X-Tenant-ID") String tenantId, @RequestBody UsuarioKeycloakRequest request) {
        usuarioService.atualizarUsuario(tenantId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> deslogarUsuario(@RequestHeader("X-Tenant-ID") String tenantId, @RequestBody UsuarioRequest request) {
        usuarioService.logoutUser(tenantId, request.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> removerUsuarioPorId(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable UUID userId) {
        usuarioService.removerUsuarioPorID(tenantId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/ativar/{userId}")
    public ResponseEntity<?> ativarUsuarioPorId(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable UUID userId) {
        usuarioService.ativarUsuario(tenantId, userId);
        return ResponseEntity.ok().build();
    }
}
