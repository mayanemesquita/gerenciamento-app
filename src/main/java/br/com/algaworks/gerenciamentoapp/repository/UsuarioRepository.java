package br.com.algaworks.gerenciamentoapp.repository;


import br.com.algaworks.gerenciamentoapp.model.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
