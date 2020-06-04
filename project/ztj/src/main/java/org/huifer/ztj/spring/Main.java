package org.huifer.ztj.spring;

import java.util.EnumSet;
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
    State<States, Events> state = stateMachine.getState();

    stateMachine.sendEvent(Events.EVENT1);
//    stateMachine.sendEvent(Events.EVENT2);
    System.out.println();
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
        State<States, Events> target = context.getTarget();
        State<States, Events> source = context.getSource();
        System.out.println(target.getId());
        System.out.println(context);
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
