package br.com.algaworks.gerenciamentoapp.controller;

import br.com.algaworks.gerenciamentoapp.model.entitys.Pessoa;
import br.com.algaworks.gerenciamentoapp.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<?> listarPessoas() {
        return new ResponseEntity<>(pessoaService.listarPessoas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.cadastrarPessoa(pessoa), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPessoa(@PathVariable Long codigo) {
        return new ResponseEntity<>(pessoaService.buscarPessoa(codigo), HttpStatus.OK);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
        return new ResponseEntity<>(pessoaService.atualizarPessoa(codigo, pessoa), HttpStatus.OK);
    }

    //Na atualização parcial de uma propriedade se expõe essa propriedade Ex: status

    @PutMapping("/{codigo}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarStatus(@PathVariable Long codigo, @Valid @RequestBody Boolean status) {
        pessoaService.atualizarStatus(codigo, status);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        pessoaService.remover(codigo);
    }
}
