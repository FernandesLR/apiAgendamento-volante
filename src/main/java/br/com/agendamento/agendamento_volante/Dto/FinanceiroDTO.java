package br.com.agendamento.agendamento_volante.Dto;

import java.time.LocalDateTime;

public record FinanceiroDTO(
        String descricao,
        float valor,
        LocalDateTime data,
        String tipoLancamento
) {
}
