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
package org.apache.ibatis.type;

import java.sql.*;
import java.time.LocalDate;
import java.time.chrono.JapaneseDate;

/**
 * Type Handler for {@link JapaneseDate}.
 *
 * @since 3.4.5
 * @author Kazuki Shimizu
 */
public class JapaneseDateTypeHandler extends BaseTypeHandler<JapaneseDate> {

    private static JapaneseDate getJapaneseDate(Date date) {
        if (date != null) {
            return JapaneseDate.from(date.toLocalDate());
        }
        return null;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JapaneseDate parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setDate(i, Date.valueOf(LocalDate.ofEpochDay(parameter.toEpochDay())));
    }

    @Override
    public JapaneseDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Date date = rs.getDate(columnName);
        return getJapaneseDate(date);
    }

    @Override
    public JapaneseDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Date date = rs.getDate(columnIndex);
        return getJapaneseDate(date);
    }

    @Override
    public JapaneseDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Date date = cs.getDate(columnIndex);
        return getJapaneseDate(date);
    }

}
