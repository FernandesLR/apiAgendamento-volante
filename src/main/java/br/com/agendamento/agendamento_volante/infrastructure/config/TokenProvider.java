package br.com.agendamento.agendamento_volante.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    @Value("${api.security.token.expiration}")
    private long expirationTime;
    @Value("${api.security.token.secret}")
    private long key;
}
