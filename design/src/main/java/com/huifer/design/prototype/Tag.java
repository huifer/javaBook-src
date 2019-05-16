package com.huifer.design.prototype;

import java.io.Serializable;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import lombok.SneakyThrows;

/**
 * <p>Title : Tag </p>
 * <p>Description :  </p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = -1732973065571551933L;
    public String f;

    public Tag(String f) {
        this.f = f;
    }

    public static void main(String[] args) {

        testLambda();

    }

    public static void testLambda() {
        List<String> list = Collections.emptyList();
        list.forEach(new Consumer<String>() {
            @SneakyThrows
            @Override
            public void accept(String o) {
                URLDecoder.decode(o, "UTF-8");
            }
        });
    }
}
