package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.LoginRequestDTO;
import br.com.agendamento.agendamento_volante.Dto.RegisterRequestDTO;
import br.com.agendamento.agendamento_volante.Dto.TokenDTO;
import br.com.agendamento.agendamento_volante.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clinica/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterRequestDTO dto) throws Exception{
        authenticationService.registrar(dto);

    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody @Valid LoginRequestDTO dto) throws Exception{
        return authenticationService.login(dto);
    }


}
