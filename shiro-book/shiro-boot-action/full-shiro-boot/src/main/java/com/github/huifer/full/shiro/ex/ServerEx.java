package com.github.huifer.full.shiro.ex;

public class ServerEx extends RuntimeException {

  private String msg;

  public ServerEx() {
  }

  public ServerEx(String message) {
    super(message);
    this.msg = message;
  }

  public ServerEx(String message, Throwable cause, String msg) {
    super(message, cause);
    this.msg = msg;
  }

  public ServerEx(Throwable cause, String msg) {
    super(cause);
    this.msg = msg;
  }

  public ServerEx(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace, String msg) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.msg = msg;
  }
}
