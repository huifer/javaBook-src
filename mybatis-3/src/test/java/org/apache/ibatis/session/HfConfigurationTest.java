package org.apache.ibatis.session;

import com.huifer.mybatis.entity.HsSell;
import com.huifer.mybatis.mapper.HsSellMapper;
import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HfConfigurationTest {

    /**
     * 测试xml配置加载
     *
     * @throws IOException
     */
    @Test
    void testXmlConfigurationLoad() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-demo.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        Configuration configuration = factory.getConfiguration();
        SqlSession sqlSession = factory.openSession();
        HsSellMapper mapper = sqlSession.getMapper(HsSellMapper.class);
        List<HsSell> list = mapper.list(2);
        List<Object> objects = sqlSession.selectList("com.huifer.mybatis.mapper.HsSellMapper.list");
        assertEquals(list.size(), objects.size());


    }

}