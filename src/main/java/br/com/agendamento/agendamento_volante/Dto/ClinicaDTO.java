package br.com.agendamento.agendamento_volante.Dto;

import br.com.agendamento.agendamento_volante.infrastructure.entity.ClinicaEntity;

public record ClinicaDTO(
    String nome, String cnpj, String endereco, String telefone, String email, String senha
) {

    public static ClinicaDTO fromEntity(ClinicaEntity clinica){
        return new ClinicaDTO(clinica.getNome(), clinica.getCnpj(),
                clinica.getEndereco(), clinica.getTelefone(), clinica.getEmail(), clinica.getSenha()
                );

    }


}
