package br.dev.rodrigocury.forum.dtos;

import br.dev.rodrigocury.forum.models.Curso;

import javax.validation.constraints.NotNull;

public class CursoDto {

  @NotNull private String nome;
  @NotNull private String categoria;

  public CursoDto() {
  }

  public CursoDto(Curso curso) {
    this.nome = curso.getNome();
    this.categoria = curso.getCategoria();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public Curso toCurso(){
    return new Curso(nome, categoria);
  }

}
