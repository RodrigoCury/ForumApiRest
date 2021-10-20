package br.dev.rodrigocury.forum.repository;

import br.dev.rodrigocury.forum.models.Curso;
import br.dev.rodrigocury.forum.repositories.CursoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.Optional;

@ActiveProfiles(profiles = "dev")
@SpringBootTest
public class CursoRespositoryTest {

  @Autowired
  private CursoRepository cursoRepository;

  @Test
  public void deveCarregarCursoAoBuscarPeloSeuId(){
    Long id = 1L;
    Optional<Curso> curso = cursoRepository.findById(id);

    Assert.isTrue(curso.isPresent(), "Curso não foi encontrado pelo ID");
  }
}
