package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.FinanceiroDTO;
import br.com.agendamento.agendamento_volante.service.FinanceiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/financeiro")
@RequiredArgsConstructor
public class FinanceiroController {

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
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarVenda(@Valid @RequestBody FinanceiroDTO dto) {
        FinanceiroDTO criado = financeiroService.cadastrarVenda(dto);
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