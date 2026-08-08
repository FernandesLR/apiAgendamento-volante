package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.AgendamentoDto;
import br.com.agendamento.agendamento_volante.Dto.AgendamentoResponseDTO;
import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;
import br.com.agendamento.agendamento_volante.infrastructure.entity.UsuarioEntity;
import br.com.agendamento.agendamento_volante.infrastructure.repository.AgendamentoRepository;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Transactional
    public void salvarAgendamento(AgendamentoDto ag, String usuarioLogado){
        // verifica se já existe um agendamento que já foi marcado para aquele mesmo dia e hora
        if(agendaRepo.existsByDataAgendada(ag.dataAgendada())){
            throw new IllegalArgumentException("Agendamento indisponivel para este horário");
        }

        validarAgendamento(ag.dataAgendada());


        UsuarioEntity clinicaLogada = clinicaRepository.findByEmail(usuarioLogado)
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

    public void validarAgendamento(LocalDateTime novaData){
        LocalDateTime inicioDia = novaData.toLocalDate().atStartOfDay();
        LocalDateTime fimDia = novaData.toLocalDate().atTime(23,59,59);

        List<AgendamentoEntity> agendamentosDoDia = agendaRepo.findByDataAgendadaBetween(inicioDia,fimDia);

        LocalDateTime novoFim = novaData.plusMinutes(45);

        for (AgendamentoEntity agendamento : agendamentosDoDia) {
            LocalDateTime existenteInicio = agendamento.getDataAgendada();
            LocalDateTime existenteFim = existenteInicio.plusMinutes(45);

            // Valida se há intersecção (tanto antes quanto depois)
            if (novaData.isBefore(existenteFim) && novoFim.isAfter(existenteInicio)) {
                throw new IllegalArgumentException(
                        "Horário indisponível! Conflito com o agendamento das "
                                + existenteInicio.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
                );
            }
        }

    }

}
