package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.AgendamentoDto;
import br.com.agendamento.agendamento_volante.Dto.AgendamentoResponseDTO;
import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;
import br.com.agendamento.agendamento_volante.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamentos")
@Validated
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping("/listarAgendamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<AgendamentoDto> listarTodosAgendamentos(){
        List<AgendamentoEntity> lista = agendamentoService.listarAgendamentos();
        return lista.stream().map(AgendamentoDto::fromEntity).toList();
    }

    @GetMapping("/meus-agendamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<AgendamentoResponseDTO> listarAgendamentosPorEmail(Authentication auth){
        String email = auth.getName();
        return agendamentoService.listarAgendamentosPorEmail(email);
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAgendamento(@Valid @RequestBody AgendamentoDto agd, Authentication auth){
        String emailLogado = auth.getName();
        agendamentoService.salvarAgendamento(agd, emailLogado);
    }

}
