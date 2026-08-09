package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.CadastroAdminDto;
import br.com.agendamento.agendamento_volante.Dto.LoginRequestDTO;
import br.com.agendamento.agendamento_volante.Dto.TokenDTO;
import br.com.agendamento.agendamento_volante.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clinica/auth/admin")
public class AdminController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarAdmin(@RequestBody @Valid CadastroAdminDto dto) throws Exception {
        authenticationService.registrarAdmin(dto);
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody @Valid LoginRequestDTO dto) throws Exception {
        return authenticationService.login(dto);
    }


}