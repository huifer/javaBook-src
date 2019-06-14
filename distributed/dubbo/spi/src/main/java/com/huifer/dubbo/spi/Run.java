package com.huifer.dubbo.spi;

import java.util.ServiceLoader;

/**
 * <p>Title : Run </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-14
 */
public class Run {

    public static void main(String[] args) {
        ServiceLoader<BaseService> baseServices = ServiceLoader.load(BaseService.class);

        for (BaseService baseService : baseServices) {
            System.out.println(baseService.hello("fff"));;
        }
    }

}
