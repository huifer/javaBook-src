package com.huifer.hibernatebook.bean;

import com.huifer.hibernatebook.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-11
 */
public class HqlTest {


    @Test
    public void demo1() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("dc");
        for (int i = 0; i < 10; i++) {
            LinkMan linkMan = new LinkMan();
            linkMan.setLkm_name("jm" + i);
            customer.getLinkMans().add(linkMan);
            session.save(linkMan);
        }
        session.save(customer);
        tx.commit();
    }

    /**
     * HQL 简单查询
     */
    @Test
    public void demo2() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query from_customer_ = session.createQuery("from Customer ");
        List<Customer> list = from_customer_.list();

        for (Customer customer : list) {
            System.out.println(customer);
        }
        tx.commit();

    }

    /**
     * 排序查询
     */
    @Test
    public void demo3() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

//        List<Customer> customer1 = session.createQuery("from Customer  order by cust_id desc ").list();
        List<Customer> customer1 = session.createQuery("from Customer  order by cust_id asc ").list();

        for (Customer customer : customer1) {
            System.out.println(customer);
        }
        tx.commit();
    }

    /**
     * 条件
     */
    @Test
    public void demo4() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Customer where cust_name = :cust_name ");
        query.setParameter("cust_name", "ac");
        List<Customer> customer1 = query.list();

        for (Customer customer : customer1) {
            System.out.println(customer);
        }
        tx.commit();
    }


    /**
     * 投影查询
     */
    @Test
    public void demo5() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("SELECT cust_name,cust_id FROM Customer ");
        List list = query.list();
        System.out.println(list);

        // 在实体类中编写构造方法，构造方法参数为你需要查询的属性
        Query query1 = session.createQuery("SELECT new Customer (cust_name) from Customer ");
        List list1 = query1.list();
        System.out.println(list1);
        tx.commit();
    }

    /**
     * 分页查询
     */
    @Test
    public void demo6() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("FROM LinkMan ");
        query.setFirstResult(0);
        query.setMaxResults(10);
        List list = query.list();
        for (Object o : list) {
            System.out.println(o);
        }
        tx.commit();
    }


    /**
     * 分组查询
     */
    @Test
    public void demo7() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("SELECT l.lkm_name, COUNT(*) FROM LinkMan as l GROUP BY l.customer");
        List<Object[]> list = query.list();
        for (Object[] o : list) {
            System.out.println(Arrays.toString(o));
        }
        tx.commit();
    }

    /**
     * Hql多表查询 内连接
     */
    @Test
    public void demo8() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("FROM Customer  c inner join c.linkMans");
        List<Object[]> list = query.list();
        for (Object[] o : list) {
            System.out.println(Arrays.toString(o));
        }
        System.out.println("--------------------------------");
        // fetch 会将数据整合 迫切内连接
        Query query1 = session.createQuery("select distinct  c FROM Customer  c inner join fetch c.linkMans");
        List<Customer> list1 = query1.list();
        for (Customer o : list1) {
            System.out.println(o);
        }

        tx.commit();
    }


    @Test
    public void demo9() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("FROM Customer  c left join c.linkMans");
        List<Object[]> list = query.list();
        for (Object[] o : list) {
            System.out.println(Arrays.toString(o));
        }

        tx.commit();
    }


}
