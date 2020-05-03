package br.com.algaworks.gerenciamentoapp.model.enumns;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TipoLancamento {

    RECEITA("Receita"),
    DESPESA("Despesa");

    private String descricao;

    TipoLancamento(String descricao) {
        this.descricao = descricao;
    }

    public static List<TipoLancamento> tipoLancamentoList() {
        return Stream.of(TipoLancamento.values())
                .collect(Collectors.toList());
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
