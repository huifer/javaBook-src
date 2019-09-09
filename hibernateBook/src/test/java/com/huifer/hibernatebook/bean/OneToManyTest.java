package com.huifer.hibernatebook.bean;

import com.huifer.hibernatebook.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 描述:
 * hibernate 关系测试类
 *
 * @author huifer
 * @date 2019-02-11
 */
public class OneToManyTest {

    @Test
    public void demo1() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // 创建两个客户
        Customer customer1 = new Customer();
        customer1.setCust_name("客户1");
        Customer customer2 = new Customer();
        customer2.setCust_name("客户2");

        // 创建三个联系人
        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkm_name("联系人1");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkm_name("联系人2");
        LinkMan linkMan3 = new LinkMan();
        linkMan3.setLkm_name("联系人3");

        // 设置关系:
        linkMan1.setCustomer(customer1);
        linkMan2.setCustomer(customer1);
        linkMan3.setCustomer(customer2);
        customer1.getLinkMans().add(linkMan1);
        customer1.getLinkMans().add(linkMan2);
        customer2.getLinkMans().add(linkMan3);

        // 保存数据:
        session.save(linkMan1);
        session.save(linkMan2);
        session.save(linkMan3);
        session.save(customer1);
        session.save(customer2);

        transaction.commit();
    }

    /**
     * 级联保存
     * * 保存客户级联联系人
     * * 客户 -> 联系人
     * *客户是主体，配置 cascade
     */
    @Test
    public void demo2() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("级联保存客户1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkm_name("级联联系人1");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        session.save(customer);

        transaction.commit();
    }


    /**
     * 级联保存
     * * 保存联系人级联客户
     * * 联系人 ->客户
     * * 联系人是主体，配置 cascade
     */
    @Test
    public void demo3() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("级联保存客户2");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkm_name("级联联系人2");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        session.save(linkMan);

        transaction.commit();
    }

    /**
     * 对象导航
     * 双方都配置 cascade
     */
    @Test
    public void demo4() {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setCust_name("cust01");

        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkm_name("LM1");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkm_name("LM2");
        LinkMan linkMan3 = new LinkMan();
        linkMan3.setLkm_name("LM3");

        linkMan1.setCustomer(customer);
        customer.getLinkMans().add(linkMan2);
        customer.getLinkMans().add(linkMan3);

        // 双方都设置了cascade
//		session.save(linkMan1); // 发送几条insert语句  4条
//		session.save(customer); // 发送几条insert语句  3条
//        session.save(linkMan2); // 发送几条insert语句  1条
        transaction.commit();

    }

    /**
     * 级联删除
     */
    @Test
    public void demo5() {

        demo1();

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 1L);
        session.delete(customer);

        transaction.commit();


    }


    /**
     * 联系人2 改成客户2
     * 提交多次sql
     * 一方放弃外键维护权
     */
    @Test
    public void demo6() {
        demo1();
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        LinkMan linkMan = session.get(LinkMan.class, 2L);
        Customer customer = session.get(Customer.class, 2L);

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        transaction.commit();
    }


    /**
     * cascade & inverse 区别
     * cascade 关联对象操作
     * inverse 外键操作
     */
    @Test
    public void demo7() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("级联保存客户1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkm_name("级联联系人1");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        //         <set name="linkMans" cascade="save-update,delete" inverse="true">
        session.save(customer); // 两个实体都会保存 但是没有外键

        transaction.commit();
    }

}
