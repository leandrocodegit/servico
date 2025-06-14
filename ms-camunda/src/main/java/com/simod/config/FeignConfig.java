package com.simod.config;
import com.simod.Exception.ExceptionAuthorization;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class FeignConfig implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BadRequestException("Erro 400 na chamada Feign para: " + methodKey);
            case 403:
                return new ExceptionAuthorization("Recurso negado");
            case 500:
                return new InternalServerErrorException("Erro interno do servidor");
            default:
                return defaultDecoder.decode(methodKey, response);
        }
    }
}
