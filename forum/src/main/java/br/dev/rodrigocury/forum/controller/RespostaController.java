package br.dev.rodrigocury.forum.controller;

import br.dev.rodrigocury.forum.Utils.CheckOptionals;
import br.dev.rodrigocury.forum.dtos.RespostaDto;
import br.dev.rodrigocury.forum.models.Resposta;
import br.dev.rodrigocury.forum.models.Topico;
import br.dev.rodrigocury.forum.models.Usuario;
import br.dev.rodrigocury.forum.repositories.RespostaRepository;
import br.dev.rodrigocury.forum.repositories.TopicoRepository;
import br.dev.rodrigocury.forum.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/respostas")
public class RespostaController {

  private final TopicoRepository topicoRepository;
  private final UsuarioRepository usuarioRepository;
  private final RespostaRepository respostaRepository;

  @Autowired
  public RespostaController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, RespostaRepository respostaRepository) {
    this.topicoRepository = topicoRepository;
    this.usuarioRepository = usuarioRepository;
    this.respostaRepository = respostaRepository;
  }

  @PostMapping
  public ResponseEntity<Resposta> criaResposta(@Valid @RequestBody RespostaDto requestResposta){
    Optional<Usuario> usuario = usuarioRepository.findById(requestResposta.getUsuarioid());
    Optional<Topico> topico = topicoRepository.findById(requestResposta.getTopicoId());

    CheckOptionals.anyOptionalsEmpty(usuario, topico);


    Resposta resposta = requestResposta.toResposta(topico.get(), usuario.get());
    respostaRepository.save(resposta);

    return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
  }


}
