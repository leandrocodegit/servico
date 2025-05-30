package br.com.usuarios.services;

import br.com.usuarios.integracao.CloudFlare;
import br.com.usuarios.integracao.TurnStyleResponse;
import br.com.usuarios.integracao.TurnStyleValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloudaFlareService {

    @Value("${cloudflare-secret}")
    private String secret;
    private final CloudFlare cloudFlare;

    public TurnStyleResponse valid(String token){
        return cloudFlare.validTurnstyle(new TurnStyleValidate(secret, token));
    }
}
