package br.dev.rodrigocury.forum.config.security;

import br.dev.rodrigocury.forum.models.Usuario;
import br.dev.rodrigocury.forum.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

  private UsuarioRepository usuarioRepository;

  @Autowired
  public AutenticacaoService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

    if (usuario.isEmpty()) throw new UsernameNotFoundException("Dados Inválidos");

    return usuario.get();
  }
}
