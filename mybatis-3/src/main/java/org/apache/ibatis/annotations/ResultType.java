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
package org.apache.ibatis.annotations;

import java.lang.annotation.*;

/**
 * This annotation can be used when a @Select method is using a
 * ResultHandler.  Those methods must have void return type, so
 * this annotation can be used to tell MyBatis what kind of object
 * it should build for each row.
 *
 * <p><br>
 * <b>How to use:</b>
 * <pre>
 * public interface UserMapper {
 *   &#064;ResultType(User.class)
 *   &#064;Select("SELECT id, name FROM users WHERE name LIKE #{name} || '%' ORDER BY id")
 *   void collectByStartingWithName(String name, ResultHandler&lt;User&gt; handler);
 * }
 * </pre>
 * @since 3.2.0
 * @author Jeff Butler
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResultType {
    /**
     * Returns the return type.
     *
     * @return the return type
     */
    Class<?> value();
}
