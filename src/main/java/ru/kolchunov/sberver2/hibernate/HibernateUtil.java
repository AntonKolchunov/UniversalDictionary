package ru.kolchunov.sberver2.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

// спец. Java класс для инициализации Hibernate
public class HibernateUtil {

    // фабрика для создания сесиий
    private static final SessionFactory sessionFactory = initSessionFactory();

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

    // этот метод будем вызывать, когда потрбуется SessionFactory
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null){
            initSessionFactory();
        }

        return sessionFactory;
    }

    // закрыть все соединения с помощью SessionFactory
    public static void close() {
        getSessionFactory().close();
    }
}