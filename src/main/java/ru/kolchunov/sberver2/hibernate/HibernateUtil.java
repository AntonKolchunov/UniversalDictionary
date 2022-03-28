package ru.kolchunov.sberver2.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Special Java class for Hibernate initialization
 */
public class HibernateUtil {

    /**
     * Session creation factory
     */
    private static final SessionFactory sessionFactory = initSessionFactory();

    /**
     * Session initialization
     */
    // этот метод вызывается автоматически, т.к. он вызывается из статичной переменной
    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure(new File("src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Get session factory
     */
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null){
            initSessionFactory();
        }

        return sessionFactory;
    }

    /**
     * Close all connections
     */
    public static void close() {
        getSessionFactory().close();
    }
}