package br.dev.rodrigocury.forum.config.validation.errorsDto;

public class ErroDeFromularioDto {

  private String campo;
  private String erro;

  public ErroDeFromularioDto(String campo, String erro) {
    this.campo = campo;
    this.erro = erro;
  }

  public ErroDeFromularioDto() {
  }

  public String getCampo() {
    return campo;
  }

  public String getErro() {
    return erro;
  }
}
