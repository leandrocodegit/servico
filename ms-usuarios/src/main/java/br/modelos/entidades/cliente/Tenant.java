package br.modelos.entidades.cliente;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "tenants")
@Getter
@Setter
@Builder
public class Tenant {
    @Id
    private UUID id;
    private String hostName;
    @DBRef
    private Cliente cliente;

    public static Tenant normalize(Tenant tenant) {
        if (tenant.getId() == null)
            tenant.setId(UUID.randomUUID());
        return tenant;
    }
}
