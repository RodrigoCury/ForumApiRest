package br.dev.rodrigocury.forum.dtos;

import br.dev.rodrigocury.forum.models.Curso;
import br.dev.rodrigocury.forum.models.Topico;
import br.dev.rodrigocury.forum.models.Usuario;

import javax.validation.constraints.NotNull;

public class TopicoDto {

  @NotNull
  private String titulo;
  @NotNull
  private String mensagem;
  @NotNull
  private Long userId;
  @NotNull
  private Long cursoId;

  public TopicoDto() {
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

}
