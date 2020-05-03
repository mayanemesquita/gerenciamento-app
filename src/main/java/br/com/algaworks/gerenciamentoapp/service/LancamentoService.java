package br.com.algaworks.gerenciamentoapp.service;

import br.com.algaworks.gerenciamentoapp.errors.exceptions.PessoaInexistenteOuInativaException;
import br.com.algaworks.gerenciamentoapp.errors.exceptions.ResourceNotFoundException;
import br.com.algaworks.gerenciamentoapp.model.domain.ResumoLancamento;
import br.com.algaworks.gerenciamentoapp.model.entitys.Lancamento;
import br.com.algaworks.gerenciamentoapp.model.entitys.Pessoa;
import br.com.algaworks.gerenciamentoapp.repository.LancamentoRepository;
import br.com.algaworks.gerenciamentoapp.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public List<Lancamento> listarLancamentos() {
        return lancamentoRepository.findAll();
    }

    public Lancamento buscarPorId(Long codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado"));
    }

    public Page<Lancamento> consultarLancamentosPorFiltros(String descricao, LocalDate dataInicial, LocalDate dataFinal, int page, int size) {
        this.validarEntrada(descricao, dataInicial, dataFinal);

        Page<Lancamento> lancamento = lancamentoRepository.consutarLancamentoByFiltros(descricao, dataInicial, dataFinal, PageRequest.of(page, size));

        if (lancamento.isEmpty())
            throw new ResourceNotFoundException("Dados não encontrados");

        return lancamento;
    }

    public Page<ResumoLancamento> consultarResumoLancamento(String descricao, LocalDate dataInicial, LocalDate dataFinal, int page, int size) {

        validarEntrada(descricao, dataInicial, dataFinal);
        Page<ResumoLancamento> resumoLancamentos = lancamentoRepository.consultarResumoLancamentoByFiltros(descricao, dataInicial, dataFinal, PageRequest.of(page, size));

        if (resumoLancamentos.isEmpty())
            throw new ResourceNotFoundException("Dados não encontrados");

        return resumoLancamentos;
    }

    public Lancamento cadastrar(Lancamento lancamento) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if (pessoa.isEmpty() || pessoa.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        categoriaService.buscarPorId(lancamento.getCategoria().getCodigo());

        return lancamentoRepository.save(lancamento);
    }

    public void deletarLancamento(Long codigo) {
        buscarPorId(codigo);
        lancamentoRepository.deleteById(codigo);
    }

    public void validarEntrada(String descricao, LocalDate dataInicial, LocalDate dataFinal) {
        if (descricao == null && dataFinal == null && dataInicial == null)
            throw new ResourceNotFoundException("É necessário preencher pelo menos um filtro de busca");
    }

}
