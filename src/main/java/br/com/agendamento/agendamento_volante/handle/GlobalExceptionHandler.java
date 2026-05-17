package br.com.agendamento.agendamento_volante.handle;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> tratarErroLogin() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("mensagem", "E-mail ou senha inválidos."));
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> tratarErroNegocio(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("mensagem", ex.getMessage()));
    }
}