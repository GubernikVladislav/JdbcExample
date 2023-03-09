package org.example.utils;

import javax.persistence.EntityManager;

/**
 * Менеджер транзакций
 */
public class TransactionManager {

    /**
     * Выполнить произволную операцию с entityManager, обернув в открытие\закрытие транзакции
     *
     * @param callback колбэк-функция, вызываемая внутри транзакции
     */
    public static void doInTransaction(TransactionCallback callback) {
        EntityManager entityManager = DbConnector.getEntityManager();
        entityManager.getTransaction().begin();

        callback.doInTransaction(entityManager);

        entityManager.getTransaction().commit();
    }
}
