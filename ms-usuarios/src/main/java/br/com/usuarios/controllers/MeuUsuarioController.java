package br.com.usuarios.controllers;

import br.com.usuarios.keycloak.models.*;
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
@RequestMapping("/conta")
@RequiredArgsConstructor
public class MeuUsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<UsuarioInfo> buscarPorId(@RequestHeader("X-Tenant-ID") String tenantId, @RequestHeader("X-User-ID") UUID id) {
        return ResponseEntity.ok(usuarioService.buscarInfoUsuarioPorID(tenantId, id));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<AuthEvent>> buscarEventosUsuario(@RequestHeader("X-Tenant-ID") String tenantId,@RequestHeader("X-User-ID") UUID id, Pageable pageable) {
        return ResponseEntity.ok(usuarioService.listaEventosUsuario(tenantId, id, pageable));
    }

    @PutMapping
    public ResponseEntity<?> atualizarUsuario(@RequestHeader("X-Tenant-ID") String tenantId, @RequestHeader("X-User-ID") UUID id, @RequestBody UsuarioKeycloakRequest request) {
        usuarioService.atualizarUsuario(tenantId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/tema")
    public ResponseEntity<?> atualizarUsuarios(@RequestHeader("X-Tenant-ID") String tenantId, @RequestHeader("X-User-ID") UUID id, @RequestBody Tema request) {
        usuarioService.atualizarTemaUsuario(tenantId, id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password")
    public ResponseEntity<?> alterarSenha(@RequestHeader("X-Tenant-ID") String tenantId, @RequestHeader("X-User-ID") UUID id, @RequestBody PasswordRequest request) {
        usuarioService.alterarUsuario(tenantId, id, request);
        return ResponseEntity.ok().build();
    }
}
