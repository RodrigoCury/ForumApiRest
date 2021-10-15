package br.dev.rodrigocury.forum.dtos;

import br.dev.rodrigocury.forum.models.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDto {

  @NotNull
  private String nome;

  @NotNull
  private String email;

  @NotNull @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
  private String senha;

  public UserDto(String nome, String email, String senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }

  public UserDto() {
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Usuario toUsuario(){
    Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuario.setEmail(email);
    usuario.setSenha(senha);
    return usuario;
  }
}
