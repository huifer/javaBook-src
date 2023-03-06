package dynamic;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.shands.mc.ddd.dao.entity.McApplication;
import com.shands.mc.ddd.domain.model.req.SendMessageReq;
import com.shands.mc.ddd.domain.model.res.scene.QueryAllEnableSceneRes;
import com.shands.mc.ddd.infrastructure.api.McSendMessage;
import com.shands.mc.ddd.infrastructure.app.ApplicationManager;
import com.shands.mc.ddd.infrastructure.scene.SceneManager;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * 初始化动态消息处理器
 */
@Service
public class InitCustomizeDynamicConsumerContainer implements ApplicationRunner {

  private static final Logger log = LoggerFactory.getLogger(
      InitCustomizeDynamicConsumerContainer.class);
  @Autowired
  private ApplicationManager applicationManager;
  @Autowired
  private SceneManager sceneManager;
  @Autowired
  @Qualifier("dynamicMqUsingImpl")
  private DynamicMqUsing dynamicMqUsing;
  @Autowired
  private McSendMessage sendMessage;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("初始化消息处理容器");
    List<QueryAllEnableSceneRes> allEnableSceneList = sceneManager.getAllEnableSceneList();
    for (QueryAllEnableSceneRes queryAllEnableSceneRes : allEnableSceneList) {
      Integer applicationId = queryAllEnableSceneRes.getApplicationId();
      McApplication byId = applicationManager.findById(applicationId);
      String sceneCode = queryAllEnableSceneRes.getSceneCode();

      DefaultMessageMqConsumer defaultMessageMqConsumer = new DefaultMessageMqConsumer();
      defaultMessageMqConsumer.setLabel(byId.getCode() + "-" + sceneCode);
      dynamicMqUsing.register(byId.getCode(), sceneCode, defaultMessageMqConsumer);
    }

  }

  /**
   * <b>注意：这是一个默认实现</b>
   */
  private class DefaultMessageMqConsumer extends AbstractMqConsumer {


    @Override
    public boolean process(Message message,
                           Channel channel) throws UnsupportedEncodingException {
      byte[] body = message.getBody();
      String json = new String(body, StandardCharsets.UTF_8);
      log.info("send json = {} ", json);
      try {

        SendMessageReq sendMessageReq = JSON.parseObject(json, SendMessageReq.class);
        sendMessage.send(sendMessageReq);
      } catch (Exception e) {
        log.error("json 解析失败= {}", json);
      }
      return true;
    }
  }
}
