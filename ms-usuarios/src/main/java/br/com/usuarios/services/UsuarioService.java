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

import static br.com.usuarios.keycloak.models.GruposName.*;


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
                .lastName(user.getLastName())
                .enabled(user.isEnabled())
                .emailVerified(user.isEmailVerified())
                .build();

        if (atributos != null) {
            var controleACesso = atributos.get("controle_acesso");
            var tema = atributos.get("tema");
            var phone = atributos.get("phone");
            info.setControle_acesso(controleACesso != null && !controleACesso.isEmpty() ? new Gson().fromJson(controleACesso.get(0).toString(), ControleAcesso.class) : null);
            info.setTema(tema != null && !tema.isEmpty() ? new Gson().fromJson(tema.get(0).toString(), Tema.class) : null);
            if (phone != null && !phone.isEmpty())
                info.setPhone(phone.get(0).toString());
        } else {
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
        var lista = userKeycloak.listaUsuarios(tenantId, false, pageable.getPageNumber(), pageable.getPageSize());
        return lista;
    }

    public List<Group> litaGrupos(String tenantId) {
        var lista = grupoKeycloak.listaGrupos(tenantId, 100);
        return lista;
    }

    public void atualizarUsuario(String tenantId, UsuarioKeycloakRequest request) {
        var user = buscarUsuarioPorID(tenantId, request.getId());

        String acesso = new Gson().toJson(request.getControleAcesso());
        String tema = new Gson().toJson(request.getTema());

        Map<String, ArrayList> attributes = new HashMap<>();
        attributes.put("tema", new ArrayList<>() {{
            add(tema);
        }});
        attributes.put("controle_acesso", new ArrayList<>() {{
            add(acesso);
        }});

        user.setAttributes(attributes);

        userKeycloak.atualizarUsuario(tenantId, request.getId(), user);

    }

    public void atualizarTemaUsuario(String tenantId, UUID userId, Tema temaRequest) {
        var user = buscarUsuarioPorID(tenantId, userId);
        String tema = new Gson().toJson(temaRequest);
        user.getAttributes().put("tema", new ArrayList<>() {{
            add(tema);
        }});

        userKeycloak.atualizarUsuario(tenantId, userId, user);

    }

    public List<AuthEvent> listaEventosUsuario(String tenantId, UUID userId, Pageable pageable) {
        var lista = eventsKeycloak.listaEventoUsuario(tenantId, userId.toString(), pageable.getPageNumber(), pageable.getPageSize());
        return lista;
    }

    @Transactional
    public void criarUsuario(String tenant, UsuarioRequest request) {
        var user = userKeycloak.buscarUsuario(tenant, request.getEmail());
        if (user == null || user.isEmpty()) {
            Map<String, ArrayList> attributes = new HashMap<>();
            attributes.put("tema", new ArrayList<>() {{
                add("{ \"dark\": true, \"color\": \"cyan\"}");
            }});
            attributes.put("controle_acesso", new ArrayList<>() {{
                add("{\"ativo\":false,\"controlarDias\":true,\"controlarHorario\":true,\"dias\":[\"SEG\",\"TER\",\"QUA\",\"QUI\",\"SEX\"],\"inicio\":\"00:00\",\"fim\":\"00:00\",\"tolerancia\":15}");
            }});
            attributes.put("grant_type", new ArrayList<>() {{
                add("password");
            }});
            userKeycloak.criarUsuario(tenant, UsuarioKeycloak.builder()
                    .attributes(attributes)
                    .username(request.getUsername())
                    .firstName(request.getFirstName())
                    .lastName(request.getFirstName())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .emailVerified(true)
                            .requiredActions(List.of("VERIFY_EMAIL", "UPDATE_PASSWORD"))
                    .enabled(true)
                    .build());
        }
        user = userKeycloak.buscarUsuario(tenant, request.getEmail());
        var userId = user.stream().findFirst();
        if (userId.isPresent()) {

            if (request.getGroups() != null) {
                request.getGroups().forEach(group -> {
                    userKeycloak.joinGrupoUsuario(tenant, userId.get().getId(), group.getId());
                });
            }

            var credentials = userKeycloak.listaCredencial(tenant, userId.get().getId());

            if (credentials.isEmpty() || credentials.stream().noneMatch(pass -> pass.getType().equals("password")))
                userKeycloak.criarCredencial(Credential.builder()
                        .temporary(false)
                        .type("password")
                        .value("Pass2020!@#$")
                        .build(), tenant, userId.get().getId());
        }
    }
}
