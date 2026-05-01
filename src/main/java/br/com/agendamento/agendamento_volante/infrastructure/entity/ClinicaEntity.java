package br.com.agendamento.agendamento_volante.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_clinicas")
public class ClinicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_clinica")
    private UUID idClinica;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String senha;

    @OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
    private List<AgendamentoEntity> agendamentos;
}