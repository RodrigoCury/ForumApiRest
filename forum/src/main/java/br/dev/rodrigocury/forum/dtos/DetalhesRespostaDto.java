package br.dev.rodrigocury.forum.dtos;

import br.dev.rodrigocury.forum.models.Resposta;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DetalhesRespostaDto {
  private Long id;
  private String mensagem;
  private LocalDateTime dataCriacao;
  private String nomeAutor;

  public DetalhesRespostaDto(Resposta resposta){
    this.id = resposta.getId();
    this.mensagem = resposta.getMensagem();
    this.dataCriacao = resposta.getDataCriacao();
    this.nomeAutor = resposta.getAutor().getNome();
  }

  public Long getId() {
    return id;
  }

  public String getMensagem() {
    return mensagem;
  }

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public String getNomeAutor() {
    return nomeAutor;
  }
}
