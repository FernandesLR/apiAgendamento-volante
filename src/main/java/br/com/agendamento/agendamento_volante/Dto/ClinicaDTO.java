package br.com.agendamento.agendamento_volante.Dto;

import br.com.agendamento.agendamento_volante.infrastructure.entity.ClinicaEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
    String email,
    @NotBlank
    @Size(min = 5, message = "A senha deve conter no mínimo 5 caracteres")
    String senha
) {

    public static ClinicaDTO fromEntity(ClinicaEntity clinica){
        return new ClinicaDTO(clinica.getNome(), clinica.getCnpj(),
                clinica.getEndereco(), clinica.getTelefone(), clinica.getEmail(), clinica.getSenha()
                );

    }


    public ClinicaEntity toEntity(){
        return ClinicaEntity.builder()
                .nome(this.nome)
                .cnpj(this.cnpj)
                .endereco(this.endereco)
                .telefone(this.telefone)
                .email(this.email)
                .senha(this.senha)
                .build();
    }


}
