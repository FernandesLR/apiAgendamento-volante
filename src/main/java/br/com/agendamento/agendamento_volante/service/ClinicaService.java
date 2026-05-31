package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.ClinicaDTO;
import br.com.agendamento.agendamento_volante.Dto.ClinicaUpdateDTO;
import br.com.agendamento.agendamento_volante.infrastructure.entity.ClinicaEntity;
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
    @Transactional
    public void alterarDados(String emailLogado, ClinicaUpdateDTO dto){
        ClinicaEntity clinica = clinicaRepo.findByEmail(emailLogado).orElseThrow(() -> new EntityNotFoundException("Usuário não cadastrado"));
        // email, senha telefone
        if(dto.email() != null){
            clinica.setEmail(dto.email());
        }if(dto.senha() != null){
            String senhaCriptografada = passwordEncoder.encode(dto.senha());
            clinica.setSenha(senhaCriptografada);
        }if(dto.telefone() != null){
            clinica.setTelefone(dto.telefone());
        }

    }


}
