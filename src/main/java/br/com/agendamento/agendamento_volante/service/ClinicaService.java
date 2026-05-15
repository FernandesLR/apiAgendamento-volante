package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.ClinicaDTO;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicaService {
    private final ClinicaRepository clinicaRepo;
    private final PasswordEncoder passwordEncoder;

    public ClinicaDTO buscarClinica(String email, String senha) {
        return clinicaRepo.findByEmail(email)
                .filter(clinica -> passwordEncoder.matches(senha, clinica.getSenha()))
                .map(ClinicaDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Credenciais inválidas"));
    }

    public void salvar(ClinicaDTO cli){
        var clinica = cli.toEntity();

        String senhaCriptografada = passwordEncoder.encode(clinica.getSenha());
        clinica.setSenha(senhaCriptografada);

        clinicaRepo.save(clinica);
    }

}
