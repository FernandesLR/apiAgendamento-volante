package br.com.agendamento.agendamento_volante.Dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        String token,
        long expiration
) {
}
