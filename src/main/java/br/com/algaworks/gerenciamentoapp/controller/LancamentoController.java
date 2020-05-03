package br.com.algaworks.gerenciamentoapp.controller;

import br.com.algaworks.gerenciamentoapp.model.entitys.Lancamento;
import br.com.algaworks.gerenciamentoapp.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("v1/lancamento")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    @Autowired
    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @GetMapping
    public ResponseEntity<?> listarLancamentos() {
        return new ResponseEntity<>(lancamentoService.listarLancamentos(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarLancamentoId(@PathVariable Long codigo) {
        return new ResponseEntity<>(lancamentoService.buscarPorId(codigo), HttpStatus.OK);
    }

    @GetMapping(path = "consultar")
    public ResponseEntity<?> consultarByFiltros(@RequestParam(value = "descricao", required = false) String descricao,
                                                @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
                                                @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        return new ResponseEntity<>(lancamentoService.consultarLancamentosPorFiltros(descricao, dataInicial, dataFinal, page, size), HttpStatus.OK);
    }

    @GetMapping(path = "resumo")
    public ResponseEntity<?> consultarResumoLancamento(@RequestParam(value = "descricao", required = false) String descricao,
                                                       @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                       @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
                                                       @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                       @RequestParam(value = "size", required = false, defaultValue = "5") int size) {

        return new ResponseEntity<>(lancamentoService.consultarResumoLancamento(descricao, dataInicial, dataFinal, page, size), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Lancamento> salvarLancamento(@Valid @RequestBody Lancamento lancamento) {
        return new ResponseEntity<>(lancamentoService.cadastrar(lancamento), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO')")
    public void deletarLancamento(@PathVariable Long codigo) {
        lancamentoService.deletarLancamento(codigo);
    }
}
