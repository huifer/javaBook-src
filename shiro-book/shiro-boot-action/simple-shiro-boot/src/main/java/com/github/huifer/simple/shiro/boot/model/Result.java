package com.github.huifer.simple.shiro.boot.model;

import lombok.Data;

@Data
public class Result {

  private int code;
  private String msg;
  private Object data;

  public Result() {
  }

  public Result(int code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public static Result ok(String msg, Object data) {
    return new Result(200, msg, data);
  }

  public static Result fail(String msg) {
    return new Result(200, msg, null);
  }


}
