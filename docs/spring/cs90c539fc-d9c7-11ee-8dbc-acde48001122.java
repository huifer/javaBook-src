package com.github.huifer.simple.shiro.boot.event.lis;

import com.github.huifer.simple.shiro.boot.event.BEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service
public class BEventLis implements ApplicationListener<BEvent> {

  private static final Logger log = LoggerFactory.getLogger(BEventLis.class);

  @Async
  @Override
  public void onApplicationEvent(BEvent event) {
    log.info("aaaaaaaaaaa");
  }
}
