package br.com.algaworks.gerenciamentoapp.service;

import br.com.algaworks.gerenciamentoapp.errors.exceptions.PessoaInexistenteOuInativaException;
import br.com.algaworks.gerenciamentoapp.errors.exceptions.ResourceNotFoundException;
import br.com.algaworks.gerenciamentoapp.model.entitys.Pessoa;
import br.com.algaworks.gerenciamentoapp.model.entitys.Usuario;
import br.com.algaworks.gerenciamentoapp.repository.PessoaRepository;
import br.com.algaworks.gerenciamentoapp.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, UsuarioRepository usuarioRepository) {
        this.pessoaRepository = pessoaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PessoaService() {

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

    public void validarPessoa(Long codigo) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
        if (pessoa.isEmpty() || pessoa.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
    }

    public Usuario buscarUsuario(String user) {
        return usuarioRepository.findByUsername(user).orElse(null);
    }

}
