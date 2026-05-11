package br.com.agendamento.agendamento_volante.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer")){
            String token = authorizationHeader.substring(7);

            if(tokenProvider.isTokenValid(token)){
                String userName = tokenProvider.getUsername(token);

            }

        }

        filterChain.doFilter(request, response);
    }
}
