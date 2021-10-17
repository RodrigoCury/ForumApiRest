package br.dev.rodrigocury.forum.dtos;

public class TokenDTO {
  private String token;
  private String tipo;

  public TokenDTO(String token, String tipo){
    this.tipo = tipo;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public String getTipo() {
    return tipo;
  }
}
