package br.com.agendamento.agendamento_volante.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroAdminDto(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Insira um email válido!")
        String email,
        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 8)
        String senha,
        @NotBlank(message = "Telefone é obrigatório")
        String telefone
) {

}
