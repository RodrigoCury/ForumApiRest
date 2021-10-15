package br.dev.rodrigocury.forum.dtos;

import br.dev.rodrigocury.forum.models.StatusTopico;
import br.dev.rodrigocury.forum.models.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DetalhesTopicoDTO {
  private Long id;
  private String titulo;
  private String mensagem;
  private LocalDateTime dataCriacao;
  private String nomeDoAutor;
  private StatusTopico statusTopico;
  private List<DetalhesRespostaDto> respostas;

  public DetalhesTopicoDTO(Topico topico) {
    this.id = topico.getId();
    this.titulo = topico.getTitulo();
    this.mensagem = topico.getMensagem();
    this.dataCriacao = topico.getDataCriacao();
    this.nomeDoAutor = topico.getAutor().getNome();
    this.statusTopico = topico.getStatus();
    this.respostas = new ArrayList<>();
    this.respostas.addAll(
        topico.getRespostas().stream().map(DetalhesRespostaDto::new).toList()
    );
  }

  public DetalhesTopicoDTO() {
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

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public String getNomeDoAutor() {
    return nomeDoAutor;
  }

  public void setNomeDoAutor(String nomeDoAutor) {
    this.nomeDoAutor = nomeDoAutor;
  }

  public StatusTopico getStatusTopico() {
    return statusTopico;
  }

  public void setStatusTopico(StatusTopico statusTopico) {
    this.statusTopico = statusTopico;
  }

  public List<DetalhesRespostaDto> getRespostas() {
    return respostas;
  }

  public void setRespostas(List<DetalhesRespostaDto> respostas) {
    this.respostas = respostas;
  }
}
