package org.example.utils;

import javax.persistence.EntityManager;

/**
 * Колбэк для вызова внутри транзакции
 */
public interface TransactionCallback {

    void doInTransaction(EntityManager entityManager);
}
