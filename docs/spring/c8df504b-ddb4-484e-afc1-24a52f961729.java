package com.github.huifer.simple.shiro.boot.event;

import org.springframework.context.ApplicationEvent;

public class BEvent extends ApplicationEvent {

  public BEvent(Object source) {
    super(source);
  }
}
