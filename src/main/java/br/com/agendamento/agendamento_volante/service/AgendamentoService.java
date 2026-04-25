package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.AgendamentoDto;
import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;
import br.com.agendamento.agendamento_volante.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendaRepo;

    public List<AgendamentoEntity> listarAgendamentos(){
        List<AgendamentoEntity> agenda = agendaRepo.findAll();

        if (agenda.isEmpty()) {
            throw new RuntimeException("Nenhum agendamento encontrado.");
        }

        return agenda;
    }

    public void salvarAgendamento(AgendamentoDto ag){
        AgendamentoEntity novaAgenda = AgendamentoEntity.builder()
                .nomeClinica(ag.nomeClinica())
                .emailClinica(ag.emailClinica())
                .dataAgendada(ag.dataAgendada())
                .endereco(ag.endereco())
                .tipoServico(ag.tipoServico())
                .animal(ag.animal())
                .status("PENDENTE")
                .build();
        agendaRepo.save(novaAgenda);
    }

}
