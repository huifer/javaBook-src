package dynamic;

public interface DynamicMqUsing extends DynamicContainer {

  <T extends AbstractMqConsumer> void register(String appCode, String sceneCode, T consumer)
      throws Exception;

}
