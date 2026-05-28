package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.ClinicaDTO;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClinicaService {
    private final ClinicaRepository clinicaRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void excluirConta(String email){
        if(clinicaRepo.findByEmail(email).isEmpty()){
            throw new EntityNotFoundException("Não é possivel deletar usuário");
        }
        clinicaRepo.deleteByEmail(email);
    }


}
