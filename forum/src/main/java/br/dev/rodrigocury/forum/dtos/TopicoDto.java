package br.dev.rodrigocury.forum.dtos;

import br.dev.rodrigocury.forum.models.Curso;
import br.dev.rodrigocury.forum.models.Topico;
import br.dev.rodrigocury.forum.models.Usuario;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopicoDto {

  @NotNull
  private String titulo;
  @NotNull
  private String mensagem;
  @NotNull
  private Long userId;
  @NotNull
  private Long cursoId;
  private Long id;

  public TopicoDto() {
  }

  public TopicoDto(Topico topico){
    this.titulo = topico.getTitulo();
    this.mensagem = topico.getMensagem();
    this.userId = topico.getAutor().getId();
    this.cursoId = topico.getCurso().getId();
    this.id = topico.getId();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getCursoId() {
    return cursoId;
  }

  public void setCursoId(Long cursoId) {
    this.cursoId = cursoId;
  }

  public Topico toTopico(Usuario usuario, Curso curso){
    return new Topico(titulo, mensagem, curso, usuario);
  }

  public static Page<TopicoDto> convertToDTO(Page<Topico> topicos){
    return topicos.map(TopicoDto::new);
  }
}
