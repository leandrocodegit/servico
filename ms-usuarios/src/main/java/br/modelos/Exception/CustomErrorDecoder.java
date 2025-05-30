package br.modelos.Exception;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String body = Util.toString(response.body().asReader());
            return new FeignClientException(response.status(), body);
        } catch (IOException e) {
            return new FeignException.FeignClientException(
                    response.status(), "Erro desconhecido", response.request(), null, null
            );
        }
    }
}
