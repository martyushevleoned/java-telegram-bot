package org.example.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SqlWorker {
    private final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    private final static Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    private final static SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();


    protected static Session getSession() {
        return sessionFactory.openSession();
    }
}