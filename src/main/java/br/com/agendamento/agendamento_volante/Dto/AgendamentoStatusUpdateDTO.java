package br.com.agendamento.agendamento_volante.Dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AgendamentoStatusUpdateDTO(
        @NotNull UUID id,
        @NotNull String status
) {
}
