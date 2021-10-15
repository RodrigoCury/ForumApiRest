package br.dev.rodrigocury.forum.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

public class AcessoInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler) {

    Acesso acesso = new Acesso(request.getRequestURI());

    request.setAttribute("acesso", acesso);

    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) {

    Acesso acesso = (Acesso) request.getAttribute("acesso");
    acesso.setDuration(Duration.between(acesso.getData(), LocalDateTime.now()));
    acesso.setStatus(response.getStatus());

    System.out.println(acesso);
  }

  public static class Acesso{

    private String path;
    private LocalDateTime data;
    private Duration duration;
    private Integer status;

    public Acesso(String path) {
      this.path = path;
      this.data = LocalDateTime.now();
    }

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }

    public LocalDateTime getData() {
      return data;
    }

    public void setData(LocalDateTime data) {
      this.data = data;
    }

    public Duration getDuration() {
      return duration;
    }

    public void setDuration(Duration duration) {
      this.duration = duration;
    }

    public Integer getStatus() {
      return status;
    }

    public void setStatus(Integer status) {
      this.status = status;
    }

    @Override
    public String toString() {
      return "Acesso{" +
          "path='" + path +
          ", data=" + data +
          ", duration=" + duration +
          ", status=" + status +
          '}';
    }
  }
}