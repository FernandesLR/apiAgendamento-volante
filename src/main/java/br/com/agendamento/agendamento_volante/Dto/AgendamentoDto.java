package br.com.agendamento.agendamento_volante.Dto;

import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record AgendamentoDto(
        @NotNull(message = "A data do agendamento é obrigatória.")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataAgendada,

        @NotBlank(message = "O tipo de serviço é obrigatório.")
        String tipoServico,

        @NotBlank(message = "A identificação do animal é obrigatória.")
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
