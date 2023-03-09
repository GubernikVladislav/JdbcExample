package org.example.dao;

import org.example.entity.Document;
import org.example.utils.DbConnector;

import javax.persistence.EntityManager;

public class DocumentDao {

    public void create(Document document) {
        EntityManager entityManager = DbConnector.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(document);

        entityManager.getTransaction().commit();
    }

    public void delete(Document document) {
        EntityManager entityManager = DbConnector.getEntityManager();
        entityManager.getTransaction().begin();

        Document document1 = entityManager.merge(document);
        entityManager.remove(document1);

        entityManager.getTransaction().commit();
    }
}
