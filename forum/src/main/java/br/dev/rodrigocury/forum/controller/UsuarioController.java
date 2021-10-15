package br.dev.rodrigocury.forum.controller;

import br.dev.rodrigocury.forum.dtos.UserDto;
import br.dev.rodrigocury.forum.models.Usuario;
import br.dev.rodrigocury.forum.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

  private final UsuarioRepository repository;

  @Autowired
  public UsuarioController(UsuarioRepository repository){
    this.repository = repository;
  }

  @GetMapping
  @ResponseBody
  public String testa(){
    return "Teste";
  }

  @PostMapping
  public ResponseEntity<Boolean> cadastraUsuario(@Valid @RequestBody UserDto requestUser){
    Usuario usuario = requestUser.toUsuario();
    repository.save(usuario);
    return ResponseEntity.ok(true);

  }
}
