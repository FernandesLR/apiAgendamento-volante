package br.com.agendamento.agendamento_volante.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @NotBlank
        @Email(message = "Email deve ser válido")
        String email,
        @NotBlank
        @Size(min = 5, message = "A senha deve conter no mínimo 5 caracteres")
        String senha
) {
}
