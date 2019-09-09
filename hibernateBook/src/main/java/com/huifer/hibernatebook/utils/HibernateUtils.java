package com.huifer.hibernatebook.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 描述:
 * hibernate工具
 *
 * @author huifer
 * @date 2019-02-11
 */
public class HibernateUtils {

    public static final Configuration HIBERNATE_CFG;
    public static final SessionFactory HIBERNATE_SESSIONFACTORY;

    static {
        HIBERNATE_CFG = new Configuration().configure();
        HIBERNATE_SESSIONFACTORY = HIBERNATE_CFG.buildSessionFactory();
    }


    public static Session getHibernateSession() {
        return HIBERNATE_SESSIONFACTORY.openSession();
    }


    public static Session getCurrentSession() {
        return HIBERNATE_SESSIONFACTORY.getCurrentSession();
    }
}

