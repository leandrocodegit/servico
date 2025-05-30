package br.modelos.dto.modulos.request;

import br.modelos.entidades.modulos.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class MenuRequest {

    private UUID id;
    private String label;
    private String icon;
    private String routerLink;
    private String separator;
    private boolean disabled;
    private String command;
    private String styleClass;
    private List<Menu> items;
}
