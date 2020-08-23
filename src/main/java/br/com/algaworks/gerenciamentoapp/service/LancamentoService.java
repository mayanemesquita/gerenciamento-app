package br.com.algaworks.gerenciamentoapp.service;

import br.com.algaworks.gerenciamentoapp.errors.exceptions.ResourceNotFoundException;
import br.com.algaworks.gerenciamentoapp.model.domain.ResumoLancamento;
import br.com.algaworks.gerenciamentoapp.model.entitys.Lancamento;
import br.com.algaworks.gerenciamentoapp.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final MessageSource messageSource;
    private final PessoaService pessoaService;
    private final CategoriaService categoriaService;

    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository, MessageSource messageSource, PessoaService pessoaService, CategoriaService categoriaService) {
        this.lancamentoRepository = lancamentoRepository;
        this.messageSource = messageSource;
        this.pessoaService = pessoaService;
        this.categoriaService = categoriaService;
    }

    public List<Lancamento> listarLancamentos() {
        return lancamentoRepository.findAll();
    }

    public Lancamento buscarPorId(Long codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado"));
    }

    public Page<Lancamento> consultarLancamentosPorFiltros(String descricao, LocalDate dataInicial, LocalDate dataFinal, int page, int size) {
        this.validarEntradaConsulta(descricao, dataInicial, dataFinal);

        Page<Lancamento> lancamento = lancamentoRepository.consutarLancamentoByFiltros(descricao, dataInicial, dataFinal, PageRequest.of(page, size));

        if (lancamento.isEmpty())
            throw new ResourceNotFoundException(messageSource.getMessage("recurso.-nao-encontrado", null, LocaleContextHolder.getLocale()));

        return lancamento;
    }

    public Page<ResumoLancamento> consultarResumoLancamento(String descricao, LocalDate dataInicial, LocalDate dataFinal, int page, int size) {

        validarEntradaConsulta(descricao, dataInicial, dataFinal);
        Page<ResumoLancamento> resumoLancamentos = lancamentoRepository.consultarResumoLancamentoByFiltros(descricao, dataInicial, dataFinal, PageRequest.of(page, size));

        if (resumoLancamentos.isEmpty())
            throw new ResourceNotFoundException(messageSource.getMessage("recurso.-nao-encontrado", null, LocaleContextHolder.getLocale()));

        return resumoLancamentos;
    }

    public Lancamento cadastrar(Lancamento lancamento) {
        pessoaService.validarPessoa(lancamento.getPessoa().getCodigo());
        categoriaService.buscarPorId(lancamento.getCategoria().getCodigo());

        return lancamentoRepository.save(lancamento);
    }

    public void deletarLancamento(Long codigo) {
        buscarPorId(codigo);
        lancamentoRepository.deleteById(codigo);
    }

    public Lancamento alterarLancamento(Long codigo) {
        Lancamento lancamento = buscarPorId(codigo);
        pessoaService.buscarPessoa(lancamento.getPessoa().getCodigo());
        return lancamentoRepository.save(lancamento);
    }

    public void validarEntradaConsulta(String descricao, LocalDate dataInicial, LocalDate dataFinal) {
        if (descricao == null && dataFinal == null && dataInicial == null)
            throw new ResourceNotFoundException("É necessário preencher pelo menos um filtro de busca");
    }

}
