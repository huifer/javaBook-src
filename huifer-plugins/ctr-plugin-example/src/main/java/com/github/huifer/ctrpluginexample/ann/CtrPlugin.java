package com.github.huifer.ctrpluginexample.ann;

import com.github.huifer.ctrpluginexample.api.InsertOrUpdateConvert;
import java.lang.annotation.ElementType;

@java.lang.annotation.Target({ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
@java.lang.annotation.Inherited
public @interface CtrPlugin {

    String uri();

    Class<?> insertParamClazz();

    Class<?> updateParamClazz();

    Class<? extends InsertOrUpdateConvert> INSERT_OR_UPDATE_CONVERT() default InsertOrUpdateConvert.class ;


}
