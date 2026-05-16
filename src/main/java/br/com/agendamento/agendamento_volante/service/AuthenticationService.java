package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.RegisterRequestDTO;
import br.com.agendamento.agendamento_volante.infrastructure.entity.ClinicaEntity;
import br.com.agendamento.agendamento_volante.infrastructure.entity.RolesEntity;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import br.com.agendamento.agendamento_volante.infrastructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClinicaRepository clinicaRepo;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public void registrar(RegisterRequestDTO dados) {

        if (clinicaRepo.findByEmail(dados.email()).isPresent()) {
            throw new RuntimeException("Usuário já cadastrado");
        }


        RolesEntity role = roleRepository.findByNome("CLINICA")
                .orElseGet(() -> {
                    RolesEntity novaRole = RolesEntity.builder().nome("CLINICA").build();
                    return roleRepository.save(novaRole);
                });




        clinicaRepo.save(ClinicaEntity.builder()
                .nome(dados.nome())
                .cnpj(dados.cnpj())
                .email(dados.email())
                .senha(passwordEncoder.encode(dados.senha()))
                .endereco(dados.endereco())
                .telefone(dados.telefone())
                .roles(Set.of(role))
                .build()
        );
    }
}