package com.huifer.hibernatebook.bean;

import com.huifer.hibernatebook.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

public class CustomerTest {


    @Test
    public void save() {
        // 加载 hibernate 配置
        Configuration cfg = new Configuration().configure();
        // sessionFactory 获取
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        // sessionFactory 获取 Session 获取
        Session session = sessionFactory.openSession();
        // 手动事务开启
        Transaction transaction = session.beginTransaction();
        // 具体逻辑

        Customer customer = new Customer();
        customer.setCust_name("测试数据");


        session.save(customer);
        // 事物提交
        transaction.commit();
        // 资源释放
        session.close();
    }

    @Test
    public void hibernateUtilsTest() {
        Session hibernateSession = HibernateUtils.getHibernateSession();
        Transaction transaction = hibernateSession.beginTransaction();
        Customer customer = new Customer();
        customer.setCust_name("hibernateUtilsTest测试数据");
        Serializable save = hibernateSession.save(customer);

        Customer customer1 = hibernateSession.get(Customer.class, 1L);
        Customer load = hibernateSession.load(Customer.class, 1L);


        customer1.setCust_name("testUpdateAAAA");

        hibernateSession.update(customer1);

//        hibernateSession.delete(customer1);

        Query from_customer = hibernateSession.createQuery("from Customer");
        List list = from_customer.list();

        System.out.println(list);

        transaction.commit();
        // 资源释放
        hibernateSession.close();
    }


    @Test
    public void threeStates() {
        Session hibernateSession = HibernateUtils.getHibernateSession();
        Transaction transaction = hibernateSession.beginTransaction();
        // 瞬时态
        Customer customer = new Customer();

        customer.setCust_name("threeStates测试数据");


        // 持久态
        Serializable saveId = hibernateSession.save(customer);
        Customer customer1 = hibernateSession.get(Customer.class, saveId);
        transaction.commit();
        // 资源释放
        hibernateSession.close();
        // 托管态
        System.out.println(customer.getCust_name());

    }

    /**
     * 持久态对象
     */
    @Test
    public void persistenceState() {
        Session hibernateSession = HibernateUtils.getHibernateSession();
        Transaction transaction = hibernateSession.beginTransaction();


        Customer customer1 = hibernateSession.get(Customer.class, 1L);
        customer1.setCust_source("aaaaaaaaaa");

        transaction.commit();
        // 资源释放
        hibernateSession.close();
    }


    /**
     * 快照区
     */
    @Test
    public void snapshot() {
        Session hibernateSession = HibernateUtils.getHibernateSession();
        Transaction transaction = hibernateSession.beginTransaction();

        Customer customer1 = hibernateSession.get(Customer.class, 1L);
        customer1.setCust_source("aaaaaaaaaa");


        transaction.commit();
        // 资源释放
        hibernateSession.close();
    }


    @Test
    public void allSession() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("allSession测试数据");


        session.save(customer);
        transaction.commit();


    }

    /**
     * query
     */
    @Test
    public void query() {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // 条件查询
        Query query1 = session.createQuery("from Customer where cust_id>2 ");
        List list = query1.list();
        System.out.println(list.size());
        query1 = session.createQuery("from Customer  where cust_name like :cust_name");
        query1.setParameter("cust_name", "a%");
        list = query1.list();
        System.out.println(list);

        // 分页查询
        query1 = session.createQuery("from Customer ");
        // 设置分页
        // 设置第几页
        query1.setFirstResult(0);
        // 每一页最多
        query1.setMaxResults(3);
        list = query1.list();
        System.out.println(list);


        transaction.commit();

    }

    /***
     * criteria
     */
    @Test
    public void criteria() {

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Customer.class);

        List list = criteria.list();
        System.out.println(list);

        Criteria cust_name = criteria.add(Restrictions.like("cust_name", "a%"));
        List list1 = cust_name.list();
        System.out.println(list1);
        transaction.commit();

    }



}
