package br.com.usuarios.controllers;

import br.com.usuarios.services.CloudaFlareService;
import br.com.usuarios.services.UsuarioService;
import br.modelos.dto.usuarios.request.UsuarioRequest;
import br.modelos.dto.usuarios.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/validate")
@RequiredArgsConstructor
public class CloudFlareController {

    private final CloudaFlareService cloudaFlareService;


    @PostMapping
    public ResponseEntity<?> criarUsuarios(@RequestParam String token){
         return ResponseEntity.ok(cloudaFlareService.valid(token));
    }
}
