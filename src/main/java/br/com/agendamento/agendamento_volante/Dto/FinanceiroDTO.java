package br.com.agendamento.agendamento_volante.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record FinanceiroDTO(
        UUID id,
        @NotBlank String emailCli,
        @NotBlank String descricao,
        @NotNull  float valor,
        @NotNull LocalDateTime data,
        @NotBlank String tipoLancamento
) {
}
