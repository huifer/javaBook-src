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
package org.apache.ibatis.submitted.encoding;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncodingTest {

    protected static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void setUp() throws Exception {
        try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/encoding/EncodingConfig.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }

        Charset charset = Resources.getCharset();
        try {
            // make sure that the SQL file has been saved in UTF-8!
            Resources.setCharset(StandardCharsets.UTF_8);
            BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
                    "org/apache/ibatis/submitted/encoding/CreateDB.sql");
        } finally {
            Resources.setCharset(charset);
        }
    }

    @Test
    void testEncoding1() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EncodingMapper mapper = sqlSession.getMapper(EncodingMapper.class);
            String answer = mapper.select1();
            assertEquals("Mara\u00f1\u00f3n", answer);
        }
    }

    @Test
    void testEncoding2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EncodingMapper mapper = sqlSession.getMapper(EncodingMapper.class);
            String answer = mapper.select2();
            assertEquals("Mara\u00f1\u00f3n", answer);
        }
    }
}
