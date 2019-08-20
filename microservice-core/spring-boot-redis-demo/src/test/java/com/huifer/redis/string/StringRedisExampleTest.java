package com.huifer.redis.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StringRedisExampleTest {

    @Autowired
    private StringRedisExample stringRedisExample;

    @Test
    public void setString() {
        stringRedisExample.setString("string", "v-string");
        String string = stringRedisExample.getString("string");
        Assert.assertEquals(string, "v-string");
    }

    @Test
    public void setList() {
        stringRedisExample.setList("string-list", "v1");
        stringRedisExample.setList("string-list", "v2");
        stringRedisExample.setList("string-list", "v3");
        stringRedisExample.setList("string-list", "v4");
        stringRedisExample.setList("string-list", "v4");
        stringRedisExample.setList("string-list", "v4");
        List<String> list = stringRedisExample.getList("string-list");
        Assert.assertTrue(list.size() == 6);
        stringRedisExample.removeAllElement("string-list", "v4");
        list = stringRedisExample.getList("string-list");
        Assert.assertTrue(list.size() == 3);
    }


    @Test
    public void setHash() {
        stringRedisExample.setHash("string-hash", "k1", "v1");
        stringRedisExample.setHash("string-hash", "k2", "v2");
        stringRedisExample.setHash("string-hash", "k3", "v3");
        stringRedisExample.setHash("string-hash", "k4", "v4");
        stringRedisExample.setHash("string-hash2", "k4", "v4");
    }

    @Test
    public void getHash() {
        Map<String, String> hash = stringRedisExample.getHash("string-hash");
        System.out.println(hash);
    }

    @Test
    public void removeK() {
        stringRedisExample.removeK("string-hash", "k4");
    }


    List<Peo> peoList = new ArrayList<>();
    Peo peo = new Peo();
    HashMap<Integer, Peo> h = new HashMap<>();

    @Before
    public void initBean() {
        peo = new Peo("张三", 10);

        for (int i = 0; i < 10; i++) {
            peoList.add(peo);
            h.put(i, peo);
        }

    }


    @Test
    public void setStringBean() {
        stringRedisExample.setStringBean("string_bean", peo);
        Peo string_bean = stringRedisExample.getStringBean("string_bean", peo.getClass());
        Assert.assertEquals(string_bean, peo);
    }


    @Test
    public void setListBean() {
        stringRedisExample.setList("string_bean_list", peoList);
        List<Peo> string_bean_list = stringRedisExample.getList("string_bean_list", peo);
        Assert.assertEquals(string_bean_list, peoList);
    }


    @Test
    public void setHashBean() {
        stringRedisExample.setHash("string_bean_hash", h);
        Map<Integer, Peo> string_bean_hash = stringRedisExample.getHash("string_bean_hash", h);
        Assert.assertEquals(string_bean_hash, h);
    }


    private static class Peo implements Serializable {

        private static final long serialVersionUID = -1226113010540557739L;
        private String name;
        private int age;

        public Peo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Peo() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Peo peo = (Peo) o;
            return age == peo.age &&
                    Objects.equals(name, peo.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
}