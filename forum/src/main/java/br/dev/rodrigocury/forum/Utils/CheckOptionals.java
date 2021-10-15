package br.dev.rodrigocury.forum.Utils;

import java.util.List;
import java.util.Optional;

public class CheckOptionals {

  public static Boolean anyOptionalsEmpty(Optional ...optionals){
    List<Optional> list = List.of(optionals);

    List<Optional> emptyOptionals = list.stream().filter(Optional::isEmpty).toList();

    return emptyOptionals.size() > 0;
  }
}
