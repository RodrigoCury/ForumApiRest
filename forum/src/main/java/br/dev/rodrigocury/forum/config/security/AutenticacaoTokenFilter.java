package br.dev.rodrigocury.forum.config.security;

import br.dev.rodrigocury.forum.models.Usuario;
import br.dev.rodrigocury.forum.repositories.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UsuarioRepository usuarioRepository;

  public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String token = recuperarToken(request);

    boolean valido = tokenService.isTokenValido(token);
    
    if(valido){
      autenticarCliente(token);
    }

    filterChain.doFilter(request, response);
  }

  private void autenticarCliente(String token) {
    Long id = tokenService.getIdUsuario(token);

    Usuario usuario = usuarioRepository.findById(id).get();

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
      return null;
    }

    return token.substring(7, token.length());
  }


}
