package br.com.usuarios.services;

import br.com.usuarios.keycloak.EventsKeycloak;
import br.com.usuarios.keycloak.GrupoKeycloak;
import br.com.usuarios.keycloak.UserKeycloak;
import br.com.usuarios.keycloak.models.*;
import br.com.usuarios.repositoryes.TenantRepository;
import br.com.usuarios.repositoryes.UsuarioRepository;
import br.modelos.dto.usuarios.request.UsuarioRequest;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TenantRepository tenantRepository;
    private final UserKeycloak userKeycloak;
    private final EventsKeycloak eventsKeycloak;
    private final KeycloakService keycloakService;
    private final GrupoKeycloak grupoKeycloak;

    public void joinGrupoUsuario(String tenantId, UUID idUsuario, UUID idGrupo, boolean isDelete) {
        if (isDelete)
            userKeycloak.unjoinGrupoUsuario(tenantId, idUsuario, idGrupo);
        else
            userKeycloak.joinGrupoUsuario(tenantId, idUsuario, idGrupo);
    }

    public UsuarioInfo buscarInfoUsuarioPorID(String tenantId, UUID id) {
        var user = userKeycloak.buscarUsuarioPorId(tenantId, id);
        var atributos = user.getAttributes();

        var info = UsuarioInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())              
                .enabled(user.isEnabled())
                .emailVerified(user.isEmailVerified())
                .build();

        if (atributos != null) {
            var controleACesso = atributos.get("controle_acesso");
            var tema = atributos.get("tema");
            info.setControle_acesso(controleACesso != null && !controleACesso.isEmpty() ? new Gson().fromJson(controleACesso.get(0).toString(), ControleAcesso.class) : null);
            info.setTema(tema != null && !tema.isEmpty() ? new Gson().fromJson(tema.get(0).toString(), Tema.class) : null); 
        }else{
            info.setControle_acesso(ControleAcesso.builder().ativo(false).build());
            info.setTema(Tema.builder().color("teal").dark(false).build());
        }
        var grupos = userKeycloak.listaGruposUsuario(tenantId, id, 100);
        info.setGroups(grupos);
        return info;
    }
    public UsuarioKeycloak buscarUsuarioPorID(String tenantId, UUID id) {
        var user = userKeycloak.buscarUsuarioPorId(tenantId, id);

        return user;
    }

    public List<UsuarioKeycloak> listaTodosUsuarios(String tenantId, Pageable pageable) {
        var lista = userKeycloak.listaUsuarios(tenantId,false, pageable.getPageNumber(), pageable.getPageSize());
        return lista;
    }

    public List<Group> litaGrupos(String tenantId) {
        var lista = grupoKeycloak.listaGrupos(tenantId, 100);
        return lista;
    }

    public void atualizarUsuario(String tenantId, UsuarioKeycloakRequest request) {
        var user = buscarUsuarioPorID(tenantId, request.getSub());

        String acesso = new Gson().toJson(request.getControleAcesso());
        String tema = new Gson().toJson(request.getTema());

        Map<String, ArrayList> attributes = new HashMap<>();
        attributes.put("tema", new ArrayList<>(){{add(tema);}});
        attributes.put("controle_acesso",  new ArrayList<>(){{add(acesso);}});

        user.setAttributes(attributes);

        userKeycloak.atualizarUsuario(tenantId, request.getSub(), user);

    }

    public List<AuthEvent> listaEventosUsuario(String tenantId, UUID userId,  Pageable pageable) {
        var lista = eventsKeycloak.listaEventoUsuario(tenantId, userId.toString(), pageable.getPageNumber(), pageable.getPageSize());
        return lista;
    }

    @Transactional
    public void criarUsuario(String tenant, UsuarioRequest request) {
//        try{
//            Map<String, Object> atributos = new HashMap<>();
//            atributos.put("tenant", tenant);
//
//            var userKeyclaok = usuarioMapper.toKeycloak(request);
//            userKeyclaok.setEnabled(true);
//            userKeyclaok.setAttributes(atributos);
//            userKeyclaok.setUsername(UUID.randomUUID().toString());
//            userKeyclaok.setCredentials(List.of(new Credencial()));
//            userKeycloak.criarUsuario(tenant, userKeyclaok);
//        }catch (Exception err){
//            System.out.println(err.getMessage());
//        }
//
//        var response = userKeycloak.buscarUsuario(request.getEmail());
//
//        if(response.isEmpty())
//            throw new ExceptionResponse("Falha ao criar usuário, servidor de credenciais não respondeu");
//        var user = usuarioMapper.toEntity(response.get(0));
//
//        Tenant tenancy = Tenant.builder()
//                .hostName(tenant)
//                .build();
//        if(tenantRepository.findByHostName(tenant).isPresent()) {
//             tenancy = tenantRepository.findByHostName(tenant).orElseThrow(() ->
//                    new ExceptionResponse("Falha ao criar usuário, servidor de credenciais não respondeu")
//            );
//
//        }else{
//            tenantRepository.save(Tenant.normalize(tenancy));
//        }
//        user.setTenant(tenancy);
//        usuarioRepository.save(Usuario.normalize(user));
    }
}
