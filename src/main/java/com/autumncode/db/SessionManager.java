package com.autumncode.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManager {
    static Logger log = LoggerFactory.getLogger(SessionManager.class);
    static StandardServiceRegistry registry;
    static SessionFactory sessionFactory;

    static {
        registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
