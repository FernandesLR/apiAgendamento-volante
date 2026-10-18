package br.com.agendamento.agendamento_volante.controller;

import br.com.agendamento.agendamento_volante.Dto.FinanceiroDTO;
import br.com.agendamento.agendamento_volante.service.FinanceiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/financeiro")
@RequiredArgsConstructor
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    // lista todos os dados do banco
    @GetMapping("/vendas")
    public ResponseEntity<List<FinanceiroDTO>> exibirVendas() {
        return ResponseEntity.ok(financeiroService.listarVendas());
    }

    // busca um registro pelo id
    @GetMapping("/vendas/{id}")
    public ResponseEntity<FinanceiroDTO> buscarVendaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(financeiroService.buscarPorId(id));
    }

    // cadastra dados
    @PostMapping("/vendas")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarVenda(@Valid @RequestBody FinanceiroDTO dto) {
        financeiroService.cadastrar(dto);
    }

    // edita ou atualiza dados
    @PutMapping("/vendas/{id}")
    public ResponseEntity<FinanceiroDTO> atualizarVenda(
            @PathVariable UUID id,
            @Valid @RequestBody FinanceiroDTO dto) {
        return ResponseEntity.ok(financeiroService.atualizar(id, dto));
    }

    // deleta dados
    @DeleteMapping("/vendas/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable UUID id) {
        financeiroService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}