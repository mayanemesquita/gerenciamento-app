package br.com.algaworks.gerenciamentoapp.model.entitys;

import br.com.algaworks.gerenciamentoapp.model.enumns.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lancamento")
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotNull
    @Size(max = 20)
    private String descricao;
    @NotNull
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private String observacao;
    @NotNull
    private TipoLancamento tipo;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cd_categoria")
    private Categoria categoria;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cd_pessoa")
    private Pessoa pessoa;

}
