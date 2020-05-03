package br.com.algaworks.gerenciamentoapp.repository;


import br.com.algaworks.gerenciamentoapp.model.entitys.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
