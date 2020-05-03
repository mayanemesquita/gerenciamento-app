package br.com.algaworks.gerenciamentoapp.controller;

import br.com.algaworks.gerenciamentoapp.model.entitys.Categoria;
import br.com.algaworks.gerenciamentoapp.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')")
    public ResponseEntity<?> categorias() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", URI.create("localhost").toString());

        return new ResponseEntity<>(categoriaService.categorias(), responseHeaders, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA')")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.cadastrar(categoria), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')")
    public ResponseEntity<?> categoria(@PathVariable Long codigo) {
        return new ResponseEntity<>(categoriaService.buscarPorId(codigo), HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        categoriaService.deletar(codigo);
    }
}
