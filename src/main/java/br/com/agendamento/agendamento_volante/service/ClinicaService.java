package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.infrastructure.entity.ClinicaEntity;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicaService {
    private final ClinicaRepository clinicaRepo;

    public ClinicaDTO bucarClinica(String email, String senha){
        ClinicaEntity clinicaEntity = clinicaRepo.findByEmailAndSenha(email, senha);

        if(clinicaEntity == null){
            throw new RuntimeException("Usuário não cadastrado");
        }

        return ClinicaDTO.fromEntity(clinicaEntity);

    }


}
