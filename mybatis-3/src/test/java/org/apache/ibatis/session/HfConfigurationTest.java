package org.apache.ibatis.session;

import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

class HfConfigurationTest {

    /**
     * 测试xml配置加载
     *
     * @throws IOException
     */
    @Test
    void testXmlConfigurationLoad() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        Configuration configuration = factory.getConfiguration();
        SqlSession sqlSession = factory.openSession();

        System.out.println();
    }

}