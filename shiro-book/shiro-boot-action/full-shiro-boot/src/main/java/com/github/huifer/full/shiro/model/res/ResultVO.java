package com.github.huifer.full.shiro.model.res;

import java.io.Serializable;

/**
 * rest接口返回对象 success
 */
public class ResultVO implements Serializable {

  public static final int SUCCESS = 20000;
  public static final int FAILED = 500;
  private Object data;
  private Integer code;
  private String message;

  public ResultVO() {
    this(FAILED, null, null);
  }

  public ResultVO(int code, Object data, String message) {
    super();
    this.code = code;
    this.data = data;
    this.message = message;
  }

  /**
   * 创建成功消息
   *
   * @return status=200, message=null, result=null
   */
  public static ResultVO success() {
    return new ResultVO(SUCCESS, null, null);
  }

  /**
   * 创建成功消息
   *
   * @return status=200, message=null, result=result
   */
  public static ResultVO success(Object result) {
    return new ResultVO(SUCCESS, result, null);
  }

  /**
   * 创建失败消息
   *
   * @return status=500, message=null, result=null
   */
  public static ResultVO failed() {
    return new ResultVO(FAILED, null, null);
  }

  /**
   * 创建失败消息
   *
   * @return status=500, message=message, result=null
   */
  public static ResultVO failed(String message) {
    return new ResultVO(FAILED, null, message);
  }

  /**
   * 创建失败消息
   *
   * @return status=status, message=message, result=null
   */
  public static ResultVO failed(int status, String message) {
    return new ResultVO(status, null, message);
  }

  /**
   * 创建失败消息
   *
   * @return status=status, message=message, result=null
   */
  public static ResultVO failed(int status, String result, String message) {
    return new ResultVO(status, result, message);
  }

  /**
   * 创建失败消息
   *
   * @return status=status, message=message, result=result
   */
  public static ResultVO failed(int status, String message, Object result) {
    return new ResultVO(status, result, message);
  }

  public boolean isSuccess() {
    return SUCCESS == this.code;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ResultVO{"
        + "result="
        + data
        + ", status="
        + code
        + ", message='"
        + message
        + '\''
        + '}';
  }
}
