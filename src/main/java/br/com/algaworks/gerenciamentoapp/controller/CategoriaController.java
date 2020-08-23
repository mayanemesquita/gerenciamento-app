package br.com.algaworks.gerenciamentoapp.controller;

import br.com.algaworks.gerenciamentoapp.integrations.cep.service.ViaCepService;
import br.com.algaworks.gerenciamentoapp.model.entitys.Categoria;
import br.com.algaworks.gerenciamentoapp.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping()
    public ResponseEntity<?> categorias() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", URI.create("localhost").toString());

        return new ResponseEntity<>(categoriaService.categorias(), responseHeaders, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.cadastrar(categoria), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<?> categoria(@PathVariable Long codigo) {
        return new ResponseEntity<>(categoriaService.buscarPorId(codigo), HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        categoriaService.deletar(codigo);
    }

    @GetMapping(path = "buscar/{cep}")
    public ResponseEntity<?> cep(@PathVariable String cep) {
        return new ResponseEntity<>(viaCepService.buscarCep(cep), HttpStatus.OK);
    }
}
