package br.com.algaworks.gerenciamentoapp.repository;


import br.com.algaworks.gerenciamentoapp.model.domain.ResumoLancamento;
import br.com.algaworks.gerenciamentoapp.model.entitys.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query(value = "SELECT L FROM Lancamento L WHERE (UPPER(L.descricao) LIKE UPPER(:descricao) " +
            "AND (L.dataVencimento >= :dataInicial OR L.dataVencimento <= :dataFinal))" +
            "OR (UPPER(L.descricao) LIKE UPPER(:descricao) )" +
            "OR (L.dataVencimento >= :dataInicial AND L.dataVencimento <= :dataFinal)")
    Page<Lancamento> consutarLancamentoByFiltros(@Param("descricao") String descricao,
                                                 @Param("dataInicial") LocalDate dataInicial,
                                                 @Param("dataFinal") LocalDate dataFinal,
                                                 Pageable pageable);


    @Query(value = "SELECT new br.com.algaworks.gerenciamentoapp.model.domain.ResumoLancamento(L.codigo, L.descricao, L.dataVencimento, L.dataPagamento, L.valor, L.observacao, L.tipo, " +
            "L.pessoa.nome, L.categoria.nome)  FROM Lancamento L " +
            "WHERE UPPER(L.descricao) LIKE UPPER(:descricao) " +
            "AND (L.dataVencimento >= :dataInicial OR L.dataVencimento <= :dataFinal)" +
            "OR (UPPER(L.descricao) LIKE UPPER(:descricao) )" +
            "OR (L.dataVencimento >= :dataInicial AND L.dataVencimento <= :dataFinal)")
    Page<ResumoLancamento> consultarResumoLancamentoByFiltros(@Param("descricao") String descricao,
                                                              @Param("dataInicial") LocalDate dataInicial,
                                                              @Param("dataFinal") LocalDate dataFinal,
                                                              Pageable pageable);
}
