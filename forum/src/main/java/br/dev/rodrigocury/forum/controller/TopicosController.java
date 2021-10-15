package br.dev.rodrigocury.forum.controller;

import br.dev.rodrigocury.forum.Utils.CheckOptionals;
import br.dev.rodrigocury.forum.dtos.DetalhesTopicoDTO;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
  public List<TopicoDto> getTopicos(@RequestParam(required = false) String nomeDoCurso) {
    List<Topico> topicos = null;

    if (nomeDoCurso == null)
      topicos = (List<Topico>) topicoRepository.findAll();
    else
      topicos = topicoRepository.findAllByNomeDoCurso(nomeDoCurso);

    return TopicoDto.convertToDTO(topicos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhesTopicoDTO> getTopicoById(@PathVariable("id") Long id) {
    Optional<Topico> topico = topicoRepository.findAllById(id);

    if (topico.isEmpty())
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
  }

  @PostMapping
  public ResponseEntity<TopicoDto> createTopico(@Valid @RequestBody TopicoDto requestTopico, UriComponentsBuilder builder) {
    Optional<Usuario> usuario = usuarioRepository.findById(requestTopico.getUserId());
    Optional<Curso> curso = cursoRepository.findById(requestTopico.getCursoId());

    boolean error = CheckOptionals.anyOptionalsEmpty(usuario, curso);

    if (error) {
      return ResponseEntity
          .badRequest().build();
    }

    Topico topico = requestTopico.toTopico(usuario.get(), curso.get());
    topicoRepository.save(topico);


    URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity
        .created(uri).body(new TopicoDto(topico));

  }
}
