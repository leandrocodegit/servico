package br.com.usuarios.configs;

import br.modelos.dto.usuarios.mapper.UsuarioMapper;
import br.modelos.dto.usuarios.mapper.UsuarioMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UsuarioMapper usuarioMapper(){
        return new UsuarioMapperImpl();
    }
}
