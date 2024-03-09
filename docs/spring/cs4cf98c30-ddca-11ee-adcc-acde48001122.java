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
package org.apache.ibatis.submitted.xml_external_ref;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterMapReferenceTest {

    private static void initDb(SqlSessionFactory sqlSessionFactory) throws IOException, SQLException {
        BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
                "org/apache/ibatis/submitted/xml_external_ref/CreateDB.sql");
    }

    @Test
    void testCrossReferenceXmlConfig() throws Exception {
        testCrossReference(getSqlSessionFactoryXmlConfig());
    }

    @Test
    void testCrossReferenceJavaConfig() throws Exception {
        testCrossReference(getSqlSessionFactoryJavaConfig());
    }

    private void testCrossReference(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ParameterMapReferencePersonMapper personMapper = sqlSession.getMapper(ParameterMapReferencePersonMapper.class);
            Person parameter = new Person();
            parameter.setId(1);
            Person person = personMapper.select(parameter);
            assertEquals((Integer) 1, person.getId());
        }
    }

    private SqlSessionFactory getSqlSessionFactoryXmlConfig() throws Exception {
        try (Reader configReader = Resources
                .getResourceAsReader("org/apache/ibatis/submitted/xml_external_ref/ParameterMapReferenceMapperConfig.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configReader);

            initDb(sqlSessionFactory);

            return sqlSessionFactory;
        }
    }

    private SqlSessionFactory getSqlSessionFactoryJavaConfig() throws Exception {
        Configuration configuration = new Configuration();
        Environment environment = new Environment("development", new JdbcTransactionFactory(), new UnpooledDataSource(
                "org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:xmlextref", null));
        configuration.setEnvironment(environment);
        configuration.addMapper(ParameterMapReferencePersonMapper.class);
        configuration.addMapper(ParameterMapReferencePetMapper.class);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        initDb(sqlSessionFactory);

        return sqlSessionFactory;
    }

}
