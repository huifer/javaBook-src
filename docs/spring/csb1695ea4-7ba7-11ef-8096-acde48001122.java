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
 * The annotation that reference a cache.
 *
 * <p>If you use this annotation, should be specified either {@link #value()} or {@link #name()} attribute.
 *
 * <p><br>
 * <b>How to use:</b>
 * <pre>
 * &#064;CacheNamespaceRef(UserMapper.class)
 * public interface AdminUserMapper {
 *   // ...
 * }
 * </pre>
 * @author Clinton Begin
 * @author Kazuki Shimizu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CacheNamespaceRef {

    /**
     * Returns the namespace type to reference a cache (the namespace name become a FQCN of specified type).
     *
     * @return the namespace type to reference a cache
     */
    Class<?> value() default void.class;

    /**
     * Returns the namespace name to reference a cache.
     *
     * @return the namespace name
     * @since 3.4.2
     */
    String name() default "";
}
