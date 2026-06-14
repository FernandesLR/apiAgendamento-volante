package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.AgendamentoDto;
import br.com.agendamento.agendamento_volante.Dto.AgendamentoResponseDTO;
import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;
import br.com.agendamento.agendamento_volante.infrastructure.entity.ClinicaEntity;
import br.com.agendamento.agendamento_volante.infrastructure.repository.AgendamentoRepository;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendaRepo;
    private final ClinicaRepository clinicaRepository;

    public List<AgendamentoEntity> listarAgendamentos(){
        List<AgendamentoEntity> agenda = agendaRepo.findAll();

        if (agenda.isEmpty()) {
            throw new RuntimeException("Nenhum agendamento encontrado.");
        }

        return agenda;
    }

    public List<AgendamentoResponseDTO> listarAgendamentosPorEmail(String emailLogado){
        List<AgendamentoEntity> agendamentos = agendaRepo.findByEmailClinica(emailLogado);

        if(agendamentos.isEmpty()){
            throw new EntityNotFoundException("Nenhum agendamento marcado");
        }

        return agendamentos.stream().map(AgendamentoResponseDTO::fromEntity).toList();
    }

    public void salvarAgendamento(AgendamentoDto ag, String usuarioLogado){
        ClinicaEntity clinicaLogada = clinicaRepository.findByEmail(usuarioLogado)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não cadastrado"));

        // converte os dados que vem do controller para entity
        AgendamentoEntity novaAgenda = ag.toEntity();


        novaAgenda.setClinica(clinicaLogada); // Faz a ligação entre a clinica e o agendamento

        novaAgenda.setNomeClinica(clinicaLogada.getNome());
        novaAgenda.setEmailClinica(clinicaLogada.getEmail());
        novaAgenda.setEndereco(clinicaLogada.getEndereco());
        novaAgenda.setStatus("PENDENTE"); // todo agendamento nasce como pendente

        agendaRepo.save(novaAgenda);
    }

}
