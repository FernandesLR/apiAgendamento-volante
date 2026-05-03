package br.com.agendamento.agendamento_volante.infrastructure.repository;

import br.com.agendamento.agendamento_volante.infrastructure.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RolesEntity, Integer> {

}
