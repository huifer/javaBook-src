package com.huifer.rest.annotation;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * <p>Title : Run </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-23
 */
public class Run {

    public static void main(String[] args) throws Exception {
        Class<T> tClass = T.class;
        Method method2 = tClass.getDeclaredMethod("method2");
        F2 annotation = method2.getAnnotation(F2.class);

        Consumer<F2> logic = new Consumer<F2>() {
            @Override
            public void accept(F2 f2a) {
                String value = f2a.value();
                System.out.println(value);
            }
        };

        F2 af2 = AnnotationUtils
                .findAnnotation(method2, F2.class);
        logic.accept(af2);


        System.out.println();
    }


}
