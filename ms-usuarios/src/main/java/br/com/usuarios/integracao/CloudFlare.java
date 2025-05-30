package br.com.usuarios.integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloudflare-turnstyle", url = "${cloudflare-url-turnstyle}")
public interface CloudFlare {

    @PostMapping
    public TurnStyleResponse validTurnstyle(@RequestBody TurnStyleValidate turnStyleValidate);
}
