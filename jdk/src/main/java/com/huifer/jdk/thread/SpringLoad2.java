package com.huifer.jdk.thread;

/**
 * <p>Title : SpringLoad2 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
public class SpringLoad2 {

    public static void main(String[] args) {
        SpringLoad2 springLoad2 = new SpringLoad2();
        springLoad2.loadProject();
    }

    public void loadProject() {
        try {
            long start = System.currentTimeMillis();
            ExecutorService service = Executors.newFixedThreadPool(3);
            CompletionService completionService = new ExecutorCompletionService(service);

            completionService.submit(this::loadSpringMvc, null);
            completionService.submit(this::loadMyBatis, null);
            completionService.submit(this::loadSpring, null);


            int count = 0;
            while (count < 3) {
                if (completionService.poll() != null) {
                    count++;
                }
            }

            service.shutdown();
            System.out.println(System.currentTimeMillis() - start);

        } catch (Exception e) {
            e.printStackTrace();

        }
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
