package br.dev.rodrigocury.forum.controller;

import br.dev.rodrigocury.forum.Utils.CheckOptionals;
import br.dev.rodrigocury.forum.dtos.AtualizacaoTopicoForm;
import br.dev.rodrigocury.forum.dtos.DetalhesTopicoDTO;
import br.dev.rodrigocury.forum.dtos.TopicoDto;
import br.dev.rodrigocury.forum.models.Curso;
import br.dev.rodrigocury.forum.models.Topico;
import br.dev.rodrigocury.forum.models.Usuario;
import br.dev.rodrigocury.forum.repositories.CursoRepository;
import br.dev.rodrigocury.forum.repositories.TopicoRepository;
import br.dev.rodrigocury.forum.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
  public Page<TopicoDto> getTopicos(
      @RequestParam(required = false) String nomeDoCurso,
      @RequestParam() int pagina,
      @RequestParam() int qtd
  ) {

    Pageable paginacao = PageRequest.of(pagina, qtd);

    Page<Topico> topicos = null;

    if (nomeDoCurso == null)
      topicos = topicoRepository.findAll(paginacao);
    else
      topicos = topicoRepository.findAllByNomeDoCurso(nomeDoCurso, paginacao);

    return TopicoDto.convertToDTO(topicos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhesTopicoDTO> getTopicoById(@PathVariable("id") Long id) {
    Optional<Topico> topico = topicoRepository.findById(id);

    if (topico.isEmpty())
      throw new EmptyResultDataAccessException(404);

    return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
  }

  @PostMapping
  public ResponseEntity<TopicoDto> createTopico(@Valid @RequestBody TopicoDto requestTopico, UriComponentsBuilder builder) {
    Optional<Usuario> usuario = usuarioRepository.findById(requestTopico.getUserId());
    Optional<Curso> curso = cursoRepository.findById(requestTopico.getCursoId());

    CheckOptionals.anyOptionalsEmpty(usuario, curso);

    Topico topico = requestTopico.toTopico(usuario.get(), curso.get());
    topicoRepository.save(topico);


    URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity
        .created(uri).body(new TopicoDto(topico));

  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicoDto> atualizaTopico(@Valid @RequestBody AtualizacaoTopicoForm topicoForm, @PathVariable("id") Long id){
    Topico topico = topicoForm.atualizar(id, topicoRepository);
    return ResponseEntity.accepted().body(new TopicoDto(topico));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<TopicoDto> removeTopico(@PathVariable("id") Long id){
      topicoRepository.deleteById(id);
      return ResponseEntity.ok().build();
  }

}
