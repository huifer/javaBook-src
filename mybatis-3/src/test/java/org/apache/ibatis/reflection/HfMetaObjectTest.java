package org.apache.ibatis.reflection;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HfMetaObjectTest {

    /**
     * java bean 测试
     */
    @Test
    void testJavaBean() {
        TestJavaBean testJavaBean = new TestJavaBean();
        MetaObject metaObject = MetaObject.forObject(testJavaBean,
                new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        metaObject.setValue("name", "bean");
        assert testJavaBean.getName().equals("bean");
    }

    /**
     * java collection 测试
     */
    @Test
    public void testCollection() {
        TestJavaBean testJavaBean = new TestJavaBean();

        testJavaBean.setName("collection");
        List<TestJavaBean> list = new ArrayList<>();
        MetaObject metaObject = MetaObject.forObject(list, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        metaObject.add(testJavaBean);
        assert list.get(0).getName().equalsIgnoreCase("collection");
    }

    /**
     * map 测试
     */
    @Test
    public void testMap() {
        TestJavaBean testJavaBean = new TestJavaBean();

        testJavaBean.setName("map");
        Map<String, TestJavaBean> map = new HashMap<>();
        MetaObject metaObject = MetaObject.forObject(map, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        metaObject.setValue("key1", testJavaBean);
        assert map.get("key1").getName().equalsIgnoreCase("map");

    }


    public static class TestJavaBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
