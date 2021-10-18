package br.dev.rodrigocury.forum.repositories;

import br.dev.rodrigocury.forum.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  @Override
  @Query("select u from Usuario u where u.id = :id")
  Optional<Usuario> findById(@Param("id") Long aLong);

  Optional<Usuario> findByEmail(String email);
}