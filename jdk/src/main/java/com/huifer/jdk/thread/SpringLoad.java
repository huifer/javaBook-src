package com.huifer.jdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title : SpringLoad </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
public class SpringLoad {

    public static void main(String[] args) {
        SpringLoad springLoad = new SpringLoad();
        springLoad.load();
    }

    private void load() {
        long startTime = System.currentTimeMillis();
        loadProject();
        long endTime = System.currentTimeMillis();
        System.out.printf("加载总共耗时:%d 毫秒\n", endTime - startTime);
    }


    private void loadProject() {
        loadSpringMvc();
        loadMyBatis();
        loadSpring();
    }


    private void loadSpringMvc() {
        loadXML("spring-mvc.xml", 1);
    }


    private void loadMyBatis() {
        loadXML("mybatis.xml", 2);
    }

    private void loadSpring() {
        loadXML("spring.xml", 3);
    }


    /***
     * 加载文件
     * @param xml
     * @param loadSec
     */
    private void loadXML(String xml, int loadSec) {
        try {
            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(loadSec);
            Thread.sleep(milliseconds);
            long endTime = System.currentTimeMillis();

            System.out.printf("[线程 : %s] 加载%s 耗时: %d 毫秒\n",
                    Thread.currentThread().getName(),
                    xml,
                    endTime - startTime
            );
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
