package br.com.algaworks.gerenciamentoapp.model.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissao")
public class Permissao {
    @Id
    private Long codigo;
    private String descricao;

}
