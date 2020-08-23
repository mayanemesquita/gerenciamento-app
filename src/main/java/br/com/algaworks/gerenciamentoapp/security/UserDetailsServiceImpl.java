package br.com.algaworks.gerenciamentoapp.security;

import br.com.algaworks.gerenciamentoapp.model.entitys.Usuario;
import br.com.algaworks.gerenciamentoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    UsuarioRepository usuarioRepository;

    @Autowired
    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha inválidos"));
        return new UserDetailsImpl(usuario);
    }
}
