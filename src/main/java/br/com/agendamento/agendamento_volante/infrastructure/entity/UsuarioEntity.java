package br.com.agendamento.agendamento_volante.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_usuario")
// Pode ser tanto clínica quanto o adm do sistema
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private UUID idUsuario;

    @Column(nullable = false, length = 150)
    private String nome;

    // CNPJ opcional para permitir ADM (a validação de obrigatoriedade fica no DTO da Clínica)
    @Column(unique = true, length = 18)
    private String cnpj;

    // Endereço opcional para permitir ADM
    private String endereco;

    // Telefone opcional para permitir ADM
    @Column(length = 20)
    private String telefone;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RolesEntity> roles;

    @OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
    private List<AgendamentoEntity> agendamentos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles == null) {
            return List.of();
        }
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNome()))
                .collect(Collectors.toSet());
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}