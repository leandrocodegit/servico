package br.com.usuarios.repositoryes;

import br.modelos.entidades.cliente.Tenant;
import br.modelos.entidades.usuarios.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;


public interface TenantRepository extends MongoRepository<Tenant, UUID> {

   Optional<Tenant> findByHostName(String hostname);
}
