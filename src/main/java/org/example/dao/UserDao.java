package org.example.dao;

import org.example.entity.User;
import org.example.utils.DbConnector;

import javax.persistence.EntityManager;

public class UserDao {

    public void create(User user) {
        EntityManager entityManager = DbConnector.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public User getUser(int id) {
        EntityManager entityManager = DbConnector.getEntityManager();
        return entityManager.find(User.class, id);
    }
}
