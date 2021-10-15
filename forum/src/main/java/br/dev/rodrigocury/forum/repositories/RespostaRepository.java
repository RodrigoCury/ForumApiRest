package br.dev.rodrigocury.forum.repositories;

import br.dev.rodrigocury.forum.models.Resposta;
import org.springframework.data.repository.CrudRepository;

public interface RespostaRepository extends CrudRepository<Resposta, Long> {
}