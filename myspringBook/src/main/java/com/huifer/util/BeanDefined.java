package com.huifer.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * beans 标签的存储
 *
 * @author huifer
 * @date 2019-03-02
 */
@Data
@NoArgsConstructor
public class BeanDefined {
    /**
     * bean id
     */
    private String beanId;
    /**
     * bean对应的全路径
     */
    private String classPath;

    /**
     * scope
     */
    private String scope = "singleton";


    /**
     * 构造bean的工厂
     */
    private String factoryBean = null;
    /**
     * 构造bean的具体方法
     */
    private String factoryMethod = null;


}
