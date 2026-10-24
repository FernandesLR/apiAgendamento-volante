package br.com.agendamento.agendamento_volante.service;

import br.com.agendamento.agendamento_volante.Dto.FinanceiroDTO;
import br.com.agendamento.agendamento_volante.infrastructure.entity.FinanceiroEntity;
import br.com.agendamento.agendamento_volante.infrastructure.entity.UsuarioEntity;
import br.com.agendamento.agendamento_volante.infrastructure.repository.ClinicaRepository;
import br.com.agendamento.agendamento_volante.infrastructure.repository.FinanceiroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class FinanceiroService {

    private final FinanceiroRepository financeiroRepository;
    private final ClinicaRepository clinicaRepository;

    @Transactional(readOnly = true)
    public List<FinanceiroDTO> listarVendas() {
        return financeiroRepository.findAll().stream().map(this::paraDTO).toList();
    }

    @Transactional(readOnly = true)
    public FinanceiroDTO buscarPorId(UUID id) {
        return paraDTO(buscarEntidade(id));
    }

    @Transactional
    public FinanceiroDTO cadastrar(FinanceiroDTO dto) {
        FinanceiroEntity venda = new FinanceiroEntity();
        preencher(venda, dto);
        return paraDTO(financeiroRepository.save(venda));
    }

    @Transactional
    public FinanceiroDTO atualizar(UUID id, FinanceiroDTO dto) {
        FinanceiroEntity venda = buscarEntidade(id);
        preencher(venda, dto);
        return paraDTO(venda);
    }

    @Transactional
    public void deletar(UUID id) {
        financeiroRepository.delete(buscarEntidade(id));
    }

    private FinanceiroEntity buscarEntidade(UUID id) {
        return financeiroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lançamento não encontrado: " + id));
    }

    private void preencher(FinanceiroEntity venda, FinanceiroDTO dto) {
        UsuarioEntity clinica = clinicaRepository.findByEmail(dto.emailCli())
                .orElseThrow(() -> new EntityNotFoundException("Clínica não encontrada: " + dto.emailCli()));

        venda.setClinicaID(clinica);
        venda.setValor(dto.valor());
        venda.setDescricao(dto.descricao());
        venda.setData(dto.data());
        venda.setTipoLancamento(dto.tipoLancamento());
    }

    private FinanceiroDTO paraDTO(FinanceiroEntity e) {
        return new FinanceiroDTO(e.getId(), e.getClinicaID().getEmail(), e.getDescricao(),
                e.getValor(), e.getData(), e.getTipoLancamento());
    }
}