package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.FinanceiroDTO;
import br.com.agendamento.agendamento_volante.dto.FinanceiroRequestDTO;
import br.com.agendamento.agendamento_volante.dto.FinanceiroResponseDTO;
import br.com.agendamento.agendamento_volante.service.FinanceiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/financeiro")
@RequiredArgsConstructor
public class FinanceiroController {

    // "final" é obrigatório para o @RequiredArgsConstructor injetar o service
    private final FinanceiroService financeiroService;

    // lista todos os dados do banco
    @GetMapping("/vendas")
    public ResponseEntity<List<FinanceiroDTO>> exibirVendas() {
        return ResponseEntity.ok(financeiroService.exibirVendas());
    }

    // busca um registro pelo id
    @GetMapping("/vendas/{id}")
    public ResponseEntity<FinanceiroDTO> buscarVendaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(financeiroService.buscarVendaPorId(id));
    }

    // cadastra dados
    @PostMapping("/vendas")
    public ResponseEntity<FinanceiroDTO> cadastrarVenda(
            @Valid @RequestBody FinanceiroDTO dto) {
        FinanceiroResponseDTO criado = financeiroService.cadastrarVenda(dto);
        URI location = URI.create("/admin/financeiro/vendas/" + criado.getId());
        return ResponseEntity.created(location).body(criado);
    }

    // edita ou atualiza dados
    @PutMapping("/vendas/{id}")
    public ResponseEntity<FinanceiroDTO> atualizarVenda(
            @PathVariable Long id,
            @Valid @RequestBody FinanceiroDTO dto) {
        return ResponseEntity.ok(financeiroService.atualizarVenda(id, dto));
    }

    // deleta dados
    @DeleteMapping("/vendas/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        financeiroService.deletarVenda(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}