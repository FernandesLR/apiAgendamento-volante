package br.com.agendamento.agendamento_volante.infrastructure.repository;

import br.com.agendamento.agendamento_volante.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClinicaRepository extends JpaRepository<UsuarioEntity, UUID> {
    Optional<UsuarioEntity> findByEmail(String email);
    Optional<UsuarioEntity> findByEmailOrCnpj(String email, String cnpj);
    void deleteByEmail(String email);

}
