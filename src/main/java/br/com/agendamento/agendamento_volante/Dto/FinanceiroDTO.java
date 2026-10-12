package br.com.agendamento.agendamento_volante.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record FinanceiroDTO(
        @NotNull UUID id,
        @NotBlank String descricao,
        @NotNull  float valor,
        @NotBlank LocalDateTime data,
        @NotBlank String tipoLancamento
) {
}
