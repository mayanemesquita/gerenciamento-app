package br.com.algaworks.gerenciamentoapp.service;

import br.com.algaworks.gerenciamentoapp.errors.exceptions.ResourceNotFoundException;
import br.com.algaworks.gerenciamentoapp.model.entitys.Pessoa;
import br.com.algaworks.gerenciamentoapp.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public void remover(Long codigo) {
        Pessoa pessoa = buscarPessoa(codigo);
        pessoaRepository.deleteById(pessoa.getCodigo());
    }

    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoa(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return cadastrarPessoa(pessoaSalva);
    }

    public void atualizarStatus(Long codigo, Boolean status) {
        Pessoa pessoaSalva = buscarPessoa(codigo);
        pessoaSalva.setStatus(status);
        cadastrarPessoa(pessoaSalva);
    }

    public Pessoa buscarPessoa(Long codigo) {
        return pessoaRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
    }

}
