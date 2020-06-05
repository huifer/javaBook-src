package org.huifer.ztj.spring;

import java.util.EnumSet;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;

public class UserLogin {



  public UserLogin() throws Exception {
  }

  public static StateMachine<UserState, UserEvent> build() throws Exception {
    Builder<UserState, UserEvent> builder = StateMachineBuilder.builder();
    // 设置状态信息
    builder.configureStates().withStates().initial(UserState.NO)
        .states(EnumSet.allOf(UserState.class));
    // 设置流转
    builder.configureTransitions()
        .withExternal().source(UserState.NO).target(UserState.LOGIN_SUCCESS).event(UserEvent.LOGIN_SUCCESS)
        .action(context -> System.out.println("登陆状态变更  成功"))
        .and()
    .withExternal().source(UserState.NO).target(UserState.LOGIN_FAILED).event(UserEvent.LOGIN_FAILED)
        .action(context -> System.out.println("变更为失败"))
    .and()
    .withExternal().source(UserState.LOGIN_SUCCESS).target(UserState.EXIT_SUCCESS).event(UserEvent.EXIT_SUCCESS)
        .action(context -> System.out.println("退出成功"))
    .and()
        .withExternal().source(UserState.LOGIN_SUCCESS).target(UserState.EXIT_FAILED).event(UserEvent.EXIT_FAILED)
        .action(context -> System.out.println("退出   失败"))


    ;
    return builder.build();
  }

  public static void main(String[] args) throws Exception {
    StateMachine<UserState, UserEvent> build = build();

    build.start();
    System.out.println(build.getState().getId());

    boolean login = true;
    if (login) {
      build.sendEvent(UserEvent.LOGIN_SUCCESS);
    } else {
      build.sendEvent(UserEvent.LOGIN_FAILED);
    }
    System.out.println(build.getState().getId());

    boolean exit = false;
    if (exit) {
      build.sendEvent(UserEvent.EXIT_SUCCESS);
    }
    else {
      build.sendEvent(UserEvent.EXIT_FAILED);
    }
    System.out.println(build.getState().getId());
  }

  enum UserState {
    NO, LOGIN_FAILED, LOGIN_SUCCESS, EXIT_SUCCESS, EXIT_FAILED
  }
  // 状态机变更流程
  // 1. 登录
  //   1. 登陆成功
  //   1. 登录失败


  enum UserEvent {
    LOGIN_SUCCESS, LOGIN_FAILED,
    EXIT_SUCCESS, EXIT_FAILED,
  }
}
