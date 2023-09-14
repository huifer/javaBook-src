package com.huifer.mybatis.factory;

import org.apache.ibatis.reflection.factory.ObjectFactory;

import java.util.List;
import java.util.Properties;

public class TestObjectFactory implements ObjectFactory {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * Sets configuration properties.
     *
     * @param properties configuration properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.data = properties.getProperty("data");
    }

    /**
     * Creates a new object with default constructor.
     *
     * @param type Object type
     * @return
     */
    @Override
    public <T> T create(Class<T> type) {
        return null;
    }

    /**
     * Creates a new object with the specified constructor and params.
     *
     * @param type                Object type
     * @param constructorArgTypes Constructor argument types
     * @param constructorArgs     Constructor argument values
     * @return
     */
    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return null;
    }

    /**
     * Returns true if this object can have a set of other objects.
     * It's main purpose is to support non-java.util.Collection objects like Scala collections.
     *
     * @param type Object type
     * @return whether it is a collection or not
     * @since 3.1.0
     */
    @Override
    public <T> boolean isCollection(Class<T> type) {
        return false;
    }
}
