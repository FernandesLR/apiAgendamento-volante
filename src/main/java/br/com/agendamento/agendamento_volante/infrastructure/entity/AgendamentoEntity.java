package br.com.agendamento.agendamento_volante.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAgenda;
    // adicionar id da clinica aqui
    @Column(nullable = false)
    private String nomeClinica;

    @Column(nullable = false)
    private String emailClinica;

    @Column(nullable = false)
    private LocalDateTime dataAgendada;

    @Column(nullable = false, unique = true)
    private String endereco;

    @Column(nullable = false)
    private String tipoServico;

    @Column(nullable = false)
    private String animal;

    @Column(nullable = false)
    private String status;

}
