package br.com.agendamento.agendamento_volante.Dto;

import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


public record AgendamentoDto(
        @NotBlank
        LocalDateTime dataAgendada,
        @NotBlank
        String tipoServico,
        @NotBlank
        String animal
) {

    public static AgendamentoDto fromEntity(AgendamentoEntity entity) {
        return new AgendamentoDto(
                entity.getDataAgendada(),
                entity.getTipoServico(),
                entity.getAnimal()
        );
    }


    public AgendamentoEntity toEntity(){
        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setDataAgendada(this.dataAgendada);
        entity.setTipoServico(this.tipoServico);
        entity.setAnimal(this.animal);
        return entity;
    }
}
