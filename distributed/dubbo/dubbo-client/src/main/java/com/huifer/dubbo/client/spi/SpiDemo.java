package com.huifer.dubbo.client.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * <p>Title : SpiDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-14
 */
public class SpiDemo {

    public static void main(String[] args) {

        ExtensionLoader<Robot> extensionLoader =
                ExtensionLoader.getExtensionLoader(Robot.class);

        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();

    }

}
