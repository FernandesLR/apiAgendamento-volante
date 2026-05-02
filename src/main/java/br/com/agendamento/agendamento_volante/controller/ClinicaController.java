package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.ClinicaDTO;
import br.com.agendamento.agendamento_volante.service.ClinicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinicas")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaService clinicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarConta(@Valid @RequestBody ClinicaDTO clinica){
        clinicaService.salvar(clinica);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ClinicaDTO login(@Valid @RequestBody ClinicaDTO clinica){
        return clinicaService.buscarClinica(clinica.email(), clinica.senha());
    }

}
