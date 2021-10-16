package br.dev.rodrigocury.forum.Utils;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public class CheckOptionals {

  public static void anyOptionalsEmpty(Optional ...optionals){
    List<Optional> list = List.of(optionals);

    List<Optional> emptyOptionals = list.stream().filter(Optional::isEmpty).toList();

    throw new EmptyResultDataAccessException(404);
  }
}
