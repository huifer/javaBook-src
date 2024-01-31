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
package org.apache.ibatis.type;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TypeAliasRegistryTest {

    @Test
    void shouldRegisterAndResolveTypeAlias() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

        typeAliasRegistry.registerAlias("rich", "org.apache.ibatis.domain.misc.RichType");

        assertEquals("org.apache.ibatis.domain.misc.RichType", typeAliasRegistry.resolveAlias("rich").getName());
    }

    /**
     * 对注解 {@link Alias} 的测试用例
     */
    @Test
    void testAnnotation() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        typeAliasRegistry.registerAlias(Hc.class);
        assertEquals("org.apache.ibatis.type.Hc", typeAliasRegistry.resolveAlias("hc").getName());
    }

    @Test
    void shouldFetchArrayType() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        assertEquals(Byte[].class, typeAliasRegistry.resolveAlias("byte[]"));
    }

    @Test
    void shouldBeAbleToRegisterSameAliasWithSameTypeAgain() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        typeAliasRegistry.registerAlias("String", String.class);
        typeAliasRegistry.registerAlias("string", String.class);
    }

    @Test
    void shouldNotBeAbleToRegisterSameAliasWithDifferentType() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        assertThrows(TypeException.class, () -> typeAliasRegistry.registerAlias("string", BigDecimal.class));
    }

    @Test
    void shouldBeAbleToRegisterAliasWithNullType() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        typeAliasRegistry.registerAlias("foo", (Class<?>) null);
        assertNull(typeAliasRegistry.resolveAlias("foo"));
    }

    @Test
    void shouldBeAbleToRegisterNewTypeIfRegisteredTypeIsNull() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        typeAliasRegistry.registerAlias("foo", (Class<?>) null);
        typeAliasRegistry.registerAlias("foo", String.class);
    }


}