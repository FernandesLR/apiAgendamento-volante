package br.com.agendamento.agendamento_volante.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${api.security.token.expiration}")
    private long expirationTime;
    @Value("${api.security.token.secret}")
    private long key;

    // gerar token
    public String gerarToken(Authentication auth){
        UserDetails user = (UserDetails) auth.getPrincipal();

        return buildToken(user.getUsername());
    }

    private String buildToken(String user){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(user)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigingKey())
                .compact();
    }

    private SecretKey getSigingKey(){
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // validar token

    public boolean isTokenValid(String token){
        try{
            getClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }

    }



    // extrair info do token
    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

    // pega o payload do token
    private Claims getClaims(String token){
        // validar assinatura
        // validar expiração
        return Jwts.parser()
                .verifyWith(getSigingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
