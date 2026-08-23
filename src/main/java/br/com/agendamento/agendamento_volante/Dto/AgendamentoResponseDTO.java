package br.com.agendamento.agendamento_volante.Dto;

import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoResponseDTO(
        UUID id,
        String nomeClinica,
        String emailClinica,
        LocalDateTime dataAgendada,
        String endereco,
        String tipoServico,
        String animal,
        String status
) {
    public static AgendamentoResponseDTO fromEntity(AgendamentoEntity entity) {
        return new AgendamentoResponseDTO(
                entity.getIdAgenda(),
                entity.getNomeClinica(),
                entity.getEmailClinica(),
                entity.getDataAgendada(),
                entity.getEndereco(),
                entity.getTipoServico(),
                entity.getAnimal(),
                entity.getStatus()
        );
    }
}
