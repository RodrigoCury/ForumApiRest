package br.dev.rodrigocury.forum.config.validation;

import br.dev.rodrigocury.forum.config.validation.errorsDto.RequestParamErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RequestParamRequiredHandler {
  private MessageSource messageSource;

  @Autowired
  public RequestParamRequiredHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public RequestParamErrorDto handle(MissingServletRequestParameterException exception){

  return new RequestParamErrorDto(exception.getParameterName() ,exception.getLocalizedMessage());

  }
}
