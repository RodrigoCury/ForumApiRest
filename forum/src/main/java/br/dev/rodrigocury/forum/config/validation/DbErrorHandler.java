package br.dev.rodrigocury.forum.config.validation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class DbErrorHandler {

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(EmptyResultDataAccessException.class)
  public String handle(EmptyResultDataAccessException exception){
    return "Não encontramos no DB o que foi pedido";
  }
}
