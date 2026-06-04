package br.com.agendamento.agendamento_volante.Dto;


import jakarta.validation.constraints.Size;

public record ClinicaUpdateDTO(
        String email,
        @Size(min = 5) String senha,
        String telefone
) {
}
