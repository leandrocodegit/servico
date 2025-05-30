package br.com.usuarios.repositoryes;

import br.modelos.entidades.usuarios.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;


public interface UsuarioRepository extends MongoRepository<Usuario, UUID> {
}
