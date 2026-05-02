package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.ClinicaDTO;
import br.com.agendamento.agendamento_volante.infrastructure.entity.ClinicaEntity;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicaService {
    private final ClinicaRepository clinicaRepo;

    public ClinicaDTO buscarClinica(String email, String senha) {
        return clinicaRepo.findByEmail(email)
                .filter(clinica -> passwordEncoder.matches(senha, clinica.getSenha()))
                .map(ClinicaDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Credenciais inválidas")); // 4. Exceção específica
    }

    public void salvar(ClinicaDTO cli){
        clinicaRepo.save(cli.toEntity());
    }

}
