package br.modelos.entidades.modulos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "menus")
@Getter
@Setter
public class Menu {

    @Id
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
