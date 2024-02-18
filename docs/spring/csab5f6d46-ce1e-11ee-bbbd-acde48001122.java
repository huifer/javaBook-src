/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.reflection.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * @author Clinton Begin
 */
public interface ObjectWrapper {

    /**
     * 根据 prop 获取属性值
     *
     * @param prop
     * @return
     */
    Object get(PropertyTokenizer prop);

    /**
     * 设置属性
     *
     * @param prop  属性值名称
     * @param value 属性值
     */
    void set(PropertyTokenizer prop, Object value);

    /**
     * 获取属性
     *
     * @param name
     * @param useCamelCaseMapping
     * @return
     */
    String findProperty(String name, boolean useCamelCaseMapping);

    /**
     * get 方法名,可读方法名
     *
     * @return
     */
    String[] getGetterNames();

    /**
     * set 方法名,可写方法名
     *
     * @return
     */
    String[] getSetterNames();

    /**
     * set 数据类型, 获取可写的数据类型
     *
     * @param name
     * @return
     */
    Class<?> getSetterType(String name);

    /**
     * get 数据类型, 获取可读的数据类型
     *
     * @param name
     * @return
     */
    Class<?> getGetterType(String name);

    /**
     * 判断是否包含set方法
     *
     * @param name
     * @return
     */
    boolean hasSetter(String name);

    /**
     * 判断是否包含get方法
     *
     * @param name
     * @return
     */
    boolean hasGetter(String name);

    /**
     * 初始化数据
     *
     * @param name
     * @param prop
     * @param objectFactory
     * @return
     */
    MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

    /**
     * 判断是不是 list
     *
     * @return
     */
    boolean isCollection();

    /**
     * list add
     *
     * @param element
     */
    void add(Object element);

    /**
     * list addAll
     *
     * @param element
     * @param <E>
     */
    <E> void addAll(List<E> element);

}
