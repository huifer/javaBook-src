package org.huifer.ztj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.StateMachineBuilder;

@SpringBootApplication
public class ZtjApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZtjApplication.class, args);

//    StateMachineBuilder.builder()
//        //配置转换
//        .configureTransitions()
//        // 设置状态
//        .withExternal()
//        // 当前节点状态
//        .source()
//        // 目标节点状态
//        .target()
//        // 导致当前状态变化的事件
//        .event()
//        // 校验规则,是否可以执行后面的 action
//        .guard()
//        // 当前事件出发后的具体行为
//        .action()
//
//    ;

  }

}
