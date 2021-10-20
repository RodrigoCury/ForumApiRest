package br.dev.rodrigocury.forum.controller;

import br.dev.rodrigocury.forum.config.security.TokenService;
import br.dev.rodrigocury.forum.dtos.LoginForm;
import br.dev.rodrigocury.forum.dtos.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@Profile(value = {"prod", "test"})
public class AutenticacaoController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping
  public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form){
    UsernamePasswordAuthenticationToken dadosLogin = form.converter();
    try {
      Authentication authenticate = authenticationManager.authenticate(dadosLogin);

      String token = tokenService.gerarToken(authenticate);
      return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
