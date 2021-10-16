package br.dev.rodrigocury.forum.config.validation.errorsDto;

public class RequestParamErrorDto {
  private String campo;
  private String erro;

  public RequestParamErrorDto(String campo, String erro) {
    this.campo = campo;
    this.erro = erro;
  }

  public RequestParamErrorDto() {
  }

  public String getCampo() {
    return campo;
  }

  public String getErro() {
    return erro;
  }
}
