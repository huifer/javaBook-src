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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class LocalDateTimeTypeHandlerTest extends BaseTypeHandlerTest {

    private static final TypeHandler<LocalDateTime> TYPE_HANDLER = new LocalDateTimeTypeHandler();
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

    @Override
    @Test
    public void shouldSetParameter() throws Exception {
        TYPE_HANDLER.setParameter(ps, 1, LOCAL_DATE_TIME, null);
        verify(ps).setObject(1, LOCAL_DATE_TIME);
    }

    @Override
    @Test
    public void shouldGetResultFromResultSetByName() throws Exception {
        when(rs.getObject("column", LocalDateTime.class)).thenReturn(LOCAL_DATE_TIME);
        assertEquals(LOCAL_DATE_TIME, TYPE_HANDLER.getResult(rs, "column"));
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    public void shouldGetResultNullFromResultSetByName() throws Exception {
        when(rs.getObject("column", LocalDateTime.class)).thenReturn(null);
        assertNull(TYPE_HANDLER.getResult(rs, "column"));
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    public void shouldGetResultFromResultSetByPosition() throws Exception {
        when(rs.getObject(1, LocalDateTime.class)).thenReturn(LOCAL_DATE_TIME);
        assertEquals(LOCAL_DATE_TIME, TYPE_HANDLER.getResult(rs, 1));
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    public void shouldGetResultNullFromResultSetByPosition() throws Exception {
        when(rs.getObject(1, LocalDateTime.class)).thenReturn(null);
        assertNull(TYPE_HANDLER.getResult(rs, 1));
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    public void shouldGetResultFromCallableStatement() throws Exception {
        when(cs.getObject(1, LocalDateTime.class)).thenReturn(LOCAL_DATE_TIME);
        assertEquals(LOCAL_DATE_TIME, TYPE_HANDLER.getResult(cs, 1));
        verify(cs, never()).wasNull();
    }

    @Override
    @Test
    public void shouldGetResultNullFromCallableStatement() throws Exception {
        when(cs.getObject(1, LocalDateTime.class)).thenReturn(null);
        assertNull(TYPE_HANDLER.getResult(cs, 1));
        verify(cs, never()).wasNull();
    }
}
