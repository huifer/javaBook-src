package com.huifer.emailtask.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>Title : MyTask </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
@Component
public class MyTask {

    private static final Logger logger = LogManager.getLogger(MyTask.class);

    @Scheduled(fixedRate = 2 * 1000)
    public void runJobA() {
        logger.info("[定时任务A]"
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(new Date()));
    }
    @Scheduled(fixedRate = 2 * 1000)
    public void runJobB() {
        logger.info("[定时任务B]"
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(new Date()));
    }


}
