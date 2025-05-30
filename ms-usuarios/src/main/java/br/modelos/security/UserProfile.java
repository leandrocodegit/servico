package br.modelos.security;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserProfile {

    private String sub;
    private String email;
    private String azp;
    private List<String> roles;
    private List<String> origins;
}
