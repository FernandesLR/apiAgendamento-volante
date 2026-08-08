package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.LoginRequestDTO;
import br.com.agendamento.agendamento_volante.Dto.RegisterRequestDTO;
import br.com.agendamento.agendamento_volante.Dto.TokenDTO;
import br.com.agendamento.agendamento_volante.infrastructure.config.TokenProvider;
import br.com.agendamento.agendamento_volante.infrastructure.entity.UsuarioEntity;
import br.com.agendamento.agendamento_volante.infrastructure.entity.RolesEntity;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import br.com.agendamento.agendamento_volante.infrastructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClinicaRepository clinicaRepo;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${api.security.token.expiration}")
    private long expirationTime;

    public void registrar(RegisterRequestDTO dados) {

        if (clinicaRepo.findByEmailOrCnpj(dados.email(), dados.cnpj()).isPresent()) {
            throw new RuntimeException("Email ou CNPJ já cadastrado no sistema.");
        }

        RolesEntity role = roleRepository.findByNome("ROLE_CLINICA")
                .orElseGet(() -> {
                    RolesEntity novaRole = RolesEntity.builder().nome("ROLE_CLINICA").build();
                    return roleRepository.save(novaRole);
                });

        UsuarioEntity novaClinica = UsuarioEntity.builder()
                .nome(dados.nome())
                .cnpj(dados.cnpj())
                .email(dados.email())
                .senha(passwordEncoder.encode(dados.senha()))
                .endereco(dados.endereco())
                .telefone(dados.telefone())
                .roles(Set.of(role))
                .build();

        clinicaRepo.save(novaClinica);
    }

    public TokenDTO login(LoginRequestDTO dto) {
        try {
            // O AuthenticationManager autentica o e-mail e valida o hash da senha automaticamente
            var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
            Authentication auth = authenticationManager.authenticate(authToken);

            String token = tokenProvider.gerarToken(auth);
            return new TokenDTO(token, expirationTime);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("E-mail ou senha inválidos.");
        }
    }
}