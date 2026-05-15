package br.com.agendamento.agendamento_volante.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank String nome,
        @NotBlank
        @Pattern(regexp = "\\d{14}")
        String cnpj,
        @NotBlank
        String endereco,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 5)
        String senha,
        String role
) {}