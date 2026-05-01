package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.ClinicaDTO;
import br.com.agendamento.agendamento_volante.service.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinicas")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaService clinicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarConta(@Validated @RequestBody ClinicaDTO clinica){
        clinicaService.salvar(clinica);

    }

}
