package org.huifer.spring.demo;

import org.huifer.spring.annotation.HFAutowired;
import org.huifer.spring.annotation.HFController;
import org.huifer.spring.annotation.HFRequestMapping;
import org.huifer.spring.annotation.HFRequestParam;

@HFController
@HFRequestMapping(name = "/demo")
public class DemoController {

  @HFAutowired
  private IDemoService demoService;

  @HFRequestMapping(name = "/test")
  public String query(
      @HFRequestParam(name = "name") String name,
      @HFRequestParam(name = "age")  int age
  ) {

    return demoService.call(name,age );
  }

}
