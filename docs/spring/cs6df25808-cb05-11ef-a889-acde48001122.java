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
package org.apache.ibatis.mapping;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * Vendor DatabaseId provider.
 * <p>
 * It returns database product name as a databaseId.
 * If the user provides a properties it uses it to translate database product name
 * key="Microsoft SQL Server", value="ms" will return "ms".
 * It can return null, if no database product name or
 * a properties was specified and no translation was found.
 *
 * @author Eduardo Macarron
 */
public class VendorDatabaseIdProvider implements DatabaseIdProvider {

    private Properties properties;

    /**
     * 返回数据源名称
     * 返回的是 mysql
     * <databaseIdProvider type="DB_VENDOR">
     * <property name="Oracle" value="oracle"/>
     * <property name="MySQL" value="mysql"/>
     * <property name="DB2" value="d2"/>
     * </databaseIdProvider>
     *
     * @param dataSource
     * @return
     */
    @Override
    public String getDatabaseId(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullPointerException("dataSource cannot be null");
        }
        try {
            return getDatabaseName(dataSource);
        } catch (Exception e) {
            LogHolder.log.error("Could not get a databaseId from dataSource", e);
        }
        return null;
    }

    @Override
    public void setProperties(Properties p) {
        this.properties = p;
    }

    /**
     * 通过数据源获取数据库类型名称
     *
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private String getDatabaseName(DataSource dataSource) throws SQLException {
        String productName = getDatabaseProductName(dataSource);
        if (this.properties != null) {
            // 通过获取到的数据库类型 获取 属性值的小写
            // properties 就是
            //  <databaseIdProvider type="DB_VENDOR">
            //    <property name="Oracle" value="oracle"/>
            //    <property name="MySQL" value="mysql"/>
            //    <property name="DB2" value="d2"/>
            //  </databaseIdProvider>

            for (Map.Entry<Object, Object> property : properties.entrySet()) {
                if (productName.contains((String) property.getKey())) {
                    return (String) property.getValue();
                }
            }
            // no match, return null
            return null;
        }
        return productName;
    }

    /**
     * 获取数据库名称 MySQL
     * 核心内容是JDBC 相关的 java.sql
     * 返回内容根据 mysql-connector-java 的 DatabaseMetaData 来
     * {@link com.mysql.jdbc.DatabaseMetaData#getDatabaseProductName()}
     *
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private String getDatabaseProductName(DataSource dataSource) throws SQLException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            DatabaseMetaData metaData = con.getMetaData();
            return metaData.getDatabaseProductName();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
        }
    }

    private static class LogHolder {
        private static final Log log = LogFactory.getLog(VendorDatabaseIdProvider.class);
    }

}
