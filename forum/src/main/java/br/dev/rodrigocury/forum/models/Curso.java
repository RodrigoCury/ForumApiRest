package br.dev.rodrigocury.forum.models;

import javax.persistence.*;
//Using generated security password: 2d81905c-14d3-40be-a674-2e780b9b5cee

@Entity
public class Curso {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String categoria;

  public Curso(String nome, String categoria) {
    this.nome = nome;
    this.categoria = categoria;
  }

  public Curso() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Curso curso = (Curso) o;
    if(this.id == null){
      if(curso.id != null){ return false; }
    } else if (!id.equals(curso.id)){
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
}
