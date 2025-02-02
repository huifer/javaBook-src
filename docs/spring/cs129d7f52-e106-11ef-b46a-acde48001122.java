/**
 * Copyright 2009-2018 the original author or authors.
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
package org.apache.ibatis.reflection.invoker;

import org.apache.ibatis.reflection.Reflector;

import java.lang.reflect.Field;

/**
 * setter 字段的方法 -> {@link Invoker}
 *
 * @author Clinton Begin
 */
public class SetFieldInvoker implements Invoker {
    /**
     * 字段
     */
    private final Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    /**
     * 执行方法如 name -> 执行 setName
     *
     * @param target
     * @param args
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Object invoke(Object target, Object[] args) throws IllegalAccessException {
        try {
            field.set(target, args[0]);
        }
        catch (IllegalAccessException e) {
            if (Reflector.canControlMemberAccessible()) {
                field.setAccessible(true);
                field.set(target, args[0]);
            }
            else {
                throw e;
            }
        }
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
