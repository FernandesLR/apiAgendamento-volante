package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.ClinicaUpdateDTO;
import br.com.agendamento.agendamento_volante.service.ClinicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/clinica")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaService clinicaService;

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public void excluirConta(Authentication auth){
        String emailLogado = auth.getName();

        clinicaService.excluirConta(emailLogado);
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public void alterarDados(@RequestBody @Valid ClinicaUpdateDTO dto, Authentication auth){
        String emailLogado = auth.getName();
        clinicaService.alterarDados(emailLogado, dto);
    }



}
