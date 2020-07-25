package org.huifer.spring.demo;

import org.huifer.spring.annotation.HFService;

@HFService(name = "demoService")
public class IDemoServiceImpl implements IDemoService {

  public String call(String name, int age) {
    return name + age;
  }
}
