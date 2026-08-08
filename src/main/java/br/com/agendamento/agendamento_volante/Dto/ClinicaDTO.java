package br.com.agendamento.agendamento_volante.Dto;

import br.com.agendamento.agendamento_volante.infrastructure.entity.UsuarioEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClinicaDTO(
    @NotBlank
    String nome,
    @NotBlank(message = "O CNPJ é obrigatório")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos numéricos")
    String cnpj,
    @NotBlank
    String endereco,
    @NotBlank
    String telefone,
    @NotBlank
    @Email(message = "Email deve ser válido")
    String email
) {

    public static ClinicaDTO fromEntity(UsuarioEntity clinica){
        return new ClinicaDTO(clinica.getNome(), clinica.getCnpj(),
                clinica.getEndereco(), clinica.getTelefone(), clinica.getEmail()
                );

    }


    public UsuarioEntity toEntity(){
        return UsuarioEntity.builder()
                .nome(this.nome)
                .cnpj(this.cnpj)
                .endereco(this.endereco)
                .telefone(this.telefone)
                .email(this.email)
                .build();
    }


}
