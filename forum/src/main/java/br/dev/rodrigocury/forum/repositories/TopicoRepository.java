package br.dev.rodrigocury.forum.repositories;

import br.dev.rodrigocury.forum.models.Topico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends CrudRepository<Topico, Long> {

  @Query("select t from Topico t join t.curso curso where curso.nome = :nomeDoCurso")
  List<Topico> findAllByNomeDoCurso(@Param("nomeDoCurso") String nomeDoCurso);

  @Override
  @Query("select t from Topico t left join fetch t.respostas where t.id = :id")
  Optional<Topico> findById(@Param("id") Long id);

}