package br.dev.rodrigocury.forum.repositories;

import br.dev.rodrigocury.forum.models.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}