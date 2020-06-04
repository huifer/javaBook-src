package org.huifer.ztj.spring;

import java.util.EnumSet;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;
import org.springframework.statemachine.state.State;

public class Main {

  public static void main(String[] args) throws Exception {
    StateMachine<States, Events> stateMachine = buildMachine();
    stateMachine.start();

//    stateMachine.sendEvent(Events.EVENT1);

    Message<Events> user_id = MessageBuilder.withPayload(Events.EVENT1).setHeader("user_id", 100)
        .build();
    stateMachine.sendEvent(user_id);
//    stateMachine.sendEvent(Events.EVENT2);
    System.out.println("发送事件之后的状态");

    printState(stateMachine);

    System.out.println();
  }

  private static void printState(StateMachine<States, Events> stateMachine) {
    State<States, Events> state = stateMachine.getState();

    States id = state.getId();
    System.out.println(id);
  }


  public static StateMachine<States, Events> buildMachine() throws Exception {
    Builder<States, Events> builder = StateMachineBuilder.builder();

    builder.configureStates()
        .withStates()
        .initial(States.STATE1)
        .end(States.STATE3)
        .states(EnumSet.allOf(States.class))
    ;
    builder.configureTransitions()
        .withExternal()
        .source(States.STATE1).target(States.STATE2)
        .event(Events.EVENT1).action(new Action<States, Events>() {
      @Override
      public void execute(
          StateContext<States, Events> context) {
        // todo: 做状态变更的事情
        Object user_id = context.getMessageHeaders().get("user_id");
        System.out.println(user_id);
        System.out.println();
      }
    })
    ;

    return builder.build();
  }

  enum States {
    STATE1, STATE2, STATE3
  }

  enum Events {
    EVENT1, EVENT2
  }
}
