package br.com.agendamento.agendamento_volante.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "tb_financeiro_lancamentos")
public class FinanceiroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "clinica_id", nullable = false)
    private UsuarioEntity clinicaID;

    @Column(nullable = false)
    private float valor;
    private String descricao;
    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String tipoLancamento;
}
