package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

}
