package br.com.agendamento.agendamento_volante.infrastructure.repository;

import br.com.agendamento.agendamento_volante.infrastructure.entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, UUID> {

    List<AgendamentoEntity> findByEmailClinica(String emailLogado);

    boolean existsByDataAgendada(LocalDateTime dataAgendada);

    List<AgendamentoEntity> findByDataAgendadaBetween(LocalDateTime inicioDoDia, LocalDateTime fimDoDia);

}
