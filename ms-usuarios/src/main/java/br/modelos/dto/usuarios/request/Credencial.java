package br.modelos.dto.usuarios.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Credencial {

    private String type;
    private String value;
    private boolean temporary;

    public Credencial() {
        this.type = "password";
        this.value = "$RG4544234%$$%$%&$%RTR";
        this.temporary = true;
    }
}
