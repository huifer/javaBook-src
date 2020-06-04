# 状态机
## 理解状态机
> 状态机是有限状态自动机的简称，是现实事物运行规则抽象而成的一个数学模型。

### 四个概念

#### 状态
- 状态 state. 一个状态机至少有2个状态. 

#### 事件
- 事件 event. 执行莫格操作触发条件或者命令.

#### 行为
- 行为 action. 事件触发之后做的事情.

#### 切换
- 切换 transition. 从一个状态切换成另一个状态

#### 实例
- 开门, 门一开始的状态是关闭,人插上钥匙旋转. 此时的事件: 插上钥匙,行为: 旋转. 如果旋转后解锁,门打开了.门的状态变成开启


## Spring 状态机
- 依赖

```xml
    <dependency>
      <groupId>org.springframework.statemachine</groupId>
      <artifactId>spring-statemachine-core</artifactId>
      <version>2.2.0.RELEASE</version>
    </dependency>

```

- 一个简单配置

```java

import org.springframework.statemachine.config.StateMachineBuilder;

    StateMachineBuilder.builder()
        //配置转换
        .configureTransitions()
        // 设置状态
        .withExternal()
        // 当前节点状态
        .source()
        // 目标节点状态
        .target()
        // 导致当前状态变化的事件
        .event()
        // 校验规则,是否可以执行后面的 action 
        .guard()
        // 当前事件出发后的具体行为
        .action()

    ;

```
### 配置详解
- source: 节点当前状态
- target: 节点目标状态
- event: 节点从当前状态变换成目标状态的事件
- guard: 校验规则,是否可以执行后面的 action
- withExternal: source 和 target 不相同
- withInternal: source 和 target 相同
- withChoice: 一个 source 多个 target 
- and: 串联
- first: 第一个分支
- last: 最后又给分支,必须有
- withStates: 状态相关设置

## 实战
- 设置状态信息

```java
    Builder<States, Events> builder = StateMachineBuilder.builder();

    builder.configureStates()
        .withStates()
        .initial(States.STATE1)
        .end(States.STATE3)
        .states(EnumSet.allOf(States.class))
    ;

```

- 设置变化以及事件相关处理逻辑

```java
    builder.configureTransitions()
        .withExternal()
        .source(States.STATE1).target(States.STATE2)
        .event(Events.EVENT1)
        .and()
        .withExternal()
        .source(States.STATE2).target(States.STATE1)
        .event(Events.EVENT2).action(new Action<States, Events>() {
      @Override
      public void execute(
          StateContext<States, Events> context) {
        System.out.println("event 2 ");
      }
    });

```

- 初始化的状态输出

```java
  public static void main(String[] args) throws Exception {
    StateMachine<States, Events> stateMachine = buildMachine();
    stateMachine.start();

    State<States, Events> state = stateMachine.getState();
    System.out.println("开始后的状态");
    States id = state.getId();
    System.out.println(id);

//    stateMachine.sendEvent(Events.EVENT1);
//    stateMachine.sendEvent(Events.EVENT2);
    System.out.println();
  }
```


- 执行结果

```text
开始后的状态
STATE1

```

- 修改 `initial(States.STATE1)` -> `initial(States.STATE3)`

```java
public static void main(String[] args) throws Exception {
    StateMachine<States, Events> stateMachine = buildMachine();
    stateMachine.start();

    State<States, Events> state = stateMachine.getState();
    System.out.println("开始后的状态");
    States id = state.getId();
    System.out.println(id);

//    stateMachine.sendEvent(Events.EVENT1);
//    stateMachine.sendEvent(Events.EVENT2);
    System.out.println();
  }
```

- 执行结果

```text
开始后的状态
STATE3

```

- 发送事件

```java
public class Main {

  public static void main(String[] args) throws Exception {
    StateMachine<States, Events> stateMachine = buildMachine();
    stateMachine.start();

    stateMachine.sendEvent(Events.EVENT1);
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
```

- 这里单纯放入一个枚举没有带入一些数据信息不够我们的实际业务.
    - 在底层我们可以找到`org.springframework.statemachine.region.Region.sendEvent(org.springframework.messaging.Message<E>)`, 自定义消息
    
```java
boolean sendEvent(Message<E> event);
```


- 修改后代码

```java
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

```