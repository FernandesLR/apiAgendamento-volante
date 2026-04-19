package br.com.agendamento.agendamento_volante.Dto;

import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoDto(
        UUID idAgenda,
        String nomeClinica,
        String emailClinica,
        LocalDateTime dataAgendada,
        String endereco,
        String tipoServico,
        String animal,
        String status
) {

    public static AgendamentoDto fromEntity(AgendamentoEntity entity) {
        return new AgendamentoDto(
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


    public AgendamentoEntity toEntity(){
        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setIdAgenda(this.idAgenda);
        entity.setNomeClinica(this.nomeClinica);
        entity.setEmailClinica(this.emailClinica);
        entity.setDataAgendada(this.dataAgendada);
        entity.setEndereco(this.endereco);
        entity.setTipoServico(this.tipoServico);
        entity.setAnimal(this.animal);
        entity.setStatus(this.status);
        return entity;
    }
}
