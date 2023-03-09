package org.example.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Класс для доступа к базе данных
 */
public class DbConnector {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main-factory");

    private DbConnector() {
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
