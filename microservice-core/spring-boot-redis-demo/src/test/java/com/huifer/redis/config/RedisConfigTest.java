package com.huifer.redis.config;

import com.huifer.redis.pojo.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier(value = "redis_2")
    private RedisTemplate<String, Object> r2;

    @Test
    public void testSet() {
        Student student = new Student();
        student.setAge(10);
        student.setName("zhangsan");

        redisTemplate.opsForValue().set("student-02", student);

        Student s = new Student();
        Object o = redisTemplate.opsForValue().get("student-02");
        BeanUtils.copyProperties(o, s);
        Assert.assertTrue(s.equals(student));

    }


    @Test
    public void testSet2() {
        Student student = new Student();
        student.setAge(10);
        student.setName("wangwu");

        r2.opsForValue().set("student-03", student);

        Student s = new Student();
        Object o = r2.opsForValue().get("student-03");
        BeanUtils.copyProperties(o, s);
        Assert.assertTrue(s.equals(student));

    }


}
