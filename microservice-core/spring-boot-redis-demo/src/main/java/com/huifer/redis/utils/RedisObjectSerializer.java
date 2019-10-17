package com.huifer.redis.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * <p>Title : RedisObjectSerializer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializeConverter = new SerializingConverter();
    private Converter<byte[], Object> deserializeConverter = new DeserializingConverter();

    @Override
    public byte[] serialize(Object o) throws SerializationException {

        if (o == null) {
            return new byte[0];
        }

        return this.serializeConverter.convert(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return this.deserializeConverter.convert(bytes);
    }

}
