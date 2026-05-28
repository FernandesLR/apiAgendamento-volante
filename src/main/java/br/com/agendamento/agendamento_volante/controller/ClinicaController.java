package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.service.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinicas")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaService clinicaService;



}
