package com.github.huifer.ctrpluginexample.ann;

import java.lang.annotation.ElementType;

@java.lang.annotation.Target({ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
@java.lang.annotation.Inherited
public @interface CtrPlugin {

    String uri();

    Class<?> insertParamClazz();

    Class<?> updateParamClazz();


}
