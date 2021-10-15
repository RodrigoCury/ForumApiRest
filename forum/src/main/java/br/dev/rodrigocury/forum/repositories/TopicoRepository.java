package br.dev.rodrigocury.forum.repositories;

import br.dev.rodrigocury.forum.models.Topico;
import org.springframework.data.repository.CrudRepository;

public interface TopicoRepository extends CrudRepository<Topico, Long> {
}