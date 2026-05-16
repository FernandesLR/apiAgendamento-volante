package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.LoginRequestDTO;
import br.com.agendamento.agendamento_volante.Dto.RegisterRequestDTO;
import br.com.agendamento.agendamento_volante.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clinica/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDTO dto) throws Exception{
        authenticationService.registrar(dto);

    }

    @PostMapping("login")
    public void login(@RequestBody @Valid LoginRequestDTO dto) throws Exception{
        authenticationService.login(dto);
    }


}
