package br.dev.rodrigocury.forum.controller;

import br.dev.rodrigocury.forum.Utils.CheckOptionals;
import br.dev.rodrigocury.forum.dtos.TopicoDto;
import br.dev.rodrigocury.forum.models.Curso;
import br.dev.rodrigocury.forum.models.Topico;
import br.dev.rodrigocury.forum.models.Usuario;
import br.dev.rodrigocury.forum.repositories.CursoRepository;
import br.dev.rodrigocury.forum.repositories.TopicoRepository;
import br.dev.rodrigocury.forum.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/topicos")
public class TopicosController {

  private final TopicoRepository topicoRepository;
  private final UsuarioRepository usuarioRepository;
  private final CursoRepository cursoRepository;

  @Autowired
  public TopicosController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
    this.topicoRepository = topicoRepository;
    this.usuarioRepository = usuarioRepository;
    this.cursoRepository = cursoRepository;
  }

  @GetMapping
  public List<TopicoDto> getTopicos(@RequestParam(required = false) String nomeDoCurso){
    List<Topico> topicos = null;

    if (nomeDoCurso == null)
      topicos = (List<Topico>) topicoRepository.findAll();
    else
      topicos = topicoRepository.findAllByNomeDoCurso(nomeDoCurso);

    return TopicoDto.convertToDTO(topicos);
  }

  @PostMapping
  public ResponseEntity createTopico(@Valid @RequestBody TopicoDto requestTopico){
    Optional<Usuario> usuario = usuarioRepository.findById(requestTopico.getUserId());
    Optional<Curso> curso = cursoRepository.findById(requestTopico.getCursoId());

    boolean error = CheckOptionals.anyOptionalsEmpty(usuario, curso);

    if (error) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("Usuario ou curso não encontrado");
    }

    Topico topico = requestTopico.toTopico(usuario.get(), curso.get());
    topicoRepository.save(topico);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(topico);

  }
}
