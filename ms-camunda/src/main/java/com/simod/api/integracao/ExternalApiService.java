package com.simod.api.integracao;

import org.camunda.bpm.engine.rest.dto.identity.GroupDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalApiService {


    @Value("${engine.url}")
    private String engileUrl;
    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object find(String path) {
        return restTemplate.getForObject(engileUrl + path, Object.class);
    }
    public List<?> findAll(String path) {
        return restTemplate.getForObject(engileUrl + path, List.class);
    }

    public void create(String path, Object post) {
         restTemplate.postForLocation(engileUrl + path, post);
    }

    public void update(String path) {
        restTemplate.put(engileUrl + path, null, Object.class);
    }
    public void update(String path, Object post) {
        restTemplate.put(engileUrl + path, post, Object.class);
    }

    public void delete(String path) {
        restTemplate.delete(engileUrl + path);
    }
}
