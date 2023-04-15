package com.huifer.fzjh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务器权重
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerWeight {

  /**
   * 权重
   */
  private int weight;
  /**
   * 当前权重
   */
  private int currentWeight;
  /**
   * 服务器ip
   */
  private String ip;
  /**
   * 服务器端口
   */
  private int port;
}
