package com.huifer.design.factory.method;

import com.huifer.design.factory.Milk;

/**
 * <p>Title : MethodFactory </p>
 * <p>Description : 工厂方法模式</p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public interface MethodFactory {

    /**
     * 获取牛奶
     */
    Milk createMilk();

}
