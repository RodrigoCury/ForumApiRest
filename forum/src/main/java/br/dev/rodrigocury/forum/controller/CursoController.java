package br.dev.rodrigocury.forum.controller;


import br.dev.rodrigocury.forum.dtos.CursoDto;
import br.dev.rodrigocury.forum.models.Curso;
import br.dev.rodrigocury.forum.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

  CursoRepository cursoRepository;

  @Autowired
  public CursoController(CursoRepository cursoRepository){
    this.cursoRepository = cursoRepository;
  }

  @PostMapping
  public Curso criaCurso(@Valid @RequestBody CursoDto requestCurso){
    Curso curso = requestCurso.toCurso();
    cursoRepository.save(curso);
    return curso;
  }
}
