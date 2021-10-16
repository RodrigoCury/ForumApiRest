package br.dev.rodrigocury.forum.dtos;

import br.dev.rodrigocury.forum.models.Topico;
import br.dev.rodrigocury.forum.repositories.TopicoRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AtualizacaoTopicoForm {
  @NotNull @NotEmpty @Length(min = 5)
  private String titulo;
  @NotNull @NotEmpty @Length(min = 5)
  private String mensagem;

  public AtualizacaoTopicoForm() {
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

  public Topico atualizar(Long id, TopicoRepository topicoRepository) {
    Optional<Topico> topico = topicoRepository.findById(id);
    if (topico.isEmpty()){
      throw new EmptyResultDataAccessException(404);
    }
    topico.get().setMensagem(mensagem);
    topico.get().setTitulo(titulo);
    return topico.get();
  }
}
