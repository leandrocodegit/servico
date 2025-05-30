package br.modelos.config;

import org.springframework.context.annotation.Configuration;


import java.util.TimeZone;

@Configuration
public class TimeZoneConfig {

    public TimeZoneConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}
