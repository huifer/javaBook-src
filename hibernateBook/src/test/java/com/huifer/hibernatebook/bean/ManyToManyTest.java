package com.huifer.hibernatebook.bean;

import com.huifer.hibernatebook.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-11
 */
public class ManyToManyTest {
    @Test
    /**
     * 保存多条记录：保存多个用户和角色
     */
    public void demo1() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 创建2个用户
        User user1 = new User();
        user1.setUser_name("用户1");
        User user2 = new User();
        user2.setUser_name("用户2");

        // 创建3个角色
        Role role1 = new Role();
        role1.setRole_name("角色A");
        Role role2 = new Role();
        role2.setRole_name("角色B");
        Role role3 = new Role();
        role3.setRole_name("角色C");

        // 设置双向的关联关系:
        user1.getRoles().add(role1);
        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        user2.getRoles().add(role2);
        user2.getRoles().add(role3);
        role1.getUsers().add(user1);
        role2.getUsers().add(user1);
        role2.getUsers().add(user2);
        role3.getUsers().add(user2);

        // 保存操作:多对多建立了双向的关系必须有一方放弃外键维护。
        // 一般是被动方放弃外键维护权。
        session.save(user1);
        session.save(user2);
        session.save(role1);
        session.save(role2);
        session.save(role3);

        tx.commit();
    }

    /**
     * 给用户选角色
     */
    @Test
    public void demo2() {
        demo1();

        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 给1号用户多选2号角色
        // 查询1号用户
        User user = session.get(User.class, 1L);
        // 查询2号角色
        Role role = session.get(Role.class, 2L);
        user.getRoles().add(role);

        tx.commit();
    }

    /***
     *给用户改选角色
     */
    @Test
    public void demo3() {
        demo1();
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // 给2号用户将原有的2号角色改为3号角色
        // 查询2号用户
        User user = session.get(User.class, 2L);
        // 查询2号角色
        Role role2 = session.get(Role.class, 2L);
        Role role3 = session.get(Role.class, 3L);
        user.getRoles().remove(role2);
        user.getRoles().add(role3);

        tx.commit();
    }
}
