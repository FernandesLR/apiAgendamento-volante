package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicaService {
    private final ClinicaRepository clinicaRepo;
    private final PasswordEncoder passwordEncoder;


}
