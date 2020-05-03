package br.com.algaworks.gerenciamentoapp.repository;


import br.com.algaworks.gerenciamentoapp.model.entitys.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
