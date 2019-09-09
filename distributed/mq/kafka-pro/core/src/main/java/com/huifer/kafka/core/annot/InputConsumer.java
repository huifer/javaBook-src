package com.huifer.kafka.core.annot;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InputConsumer {
    String propertiesFile() default "";

    String topic() default "";
}
