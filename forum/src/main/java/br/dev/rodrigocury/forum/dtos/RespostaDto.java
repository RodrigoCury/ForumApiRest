package br.dev.rodrigocury.forum.dtos;

import br.dev.rodrigocury.forum.models.Resposta;
import br.dev.rodrigocury.forum.models.Topico;
import br.dev.rodrigocury.forum.models.Usuario;

import javax.validation.constraints.NotNull;

public class RespostaDto {

  @NotNull
  private String mensagem;
  @NotNull
  private Long topicoId;
  @NotNull
  private Long usuarioid;

  public RespostaDto() {
  }

  public RespostaDto(Resposta resposta) {
    this.mensagem = resposta.getMensagem();
    this.topicoId = resposta.getTopico().getId();
    this.usuarioid = resposta.getAutor().getId();
  }

  public Long getTopicoId() {
    return topicoId;
  }

  public void setTopicoId(Long topicoId) {
    this.topicoId = topicoId;
  }

  public Long getUsuarioid() {
    return usuarioid;
  }

  public void setUsuarioid(Long usuarioid) {
    this.usuarioid = usuarioid;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public Resposta toResposta(Topico topico, Usuario usuario){
    return new Resposta(mensagem, topico, usuario);
  }
}
