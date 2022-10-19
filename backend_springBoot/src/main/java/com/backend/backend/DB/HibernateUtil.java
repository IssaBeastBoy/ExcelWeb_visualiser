package com.backend.backend.DB;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try{
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }catch (Throwable h){
            throw new ExceptionInInitializerError(h);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
