package br.modelos.dto.usuarios.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRequest {

    private String id;
    @NotEmpty(message = "Documento não pode ser vazio")
    private String documento;
    @NotEmpty(message = "Nome não pode ser vazio")
    private String firstName;
    @NotEmpty(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;
}
