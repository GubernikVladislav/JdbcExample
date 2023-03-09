package org.example.dao;

import org.example.model.Director;
import org.example.utils.DbConnector;
import org.example.utils.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DirectorDao {

    /**
     * Сохранение режисёра
     *
     * @param director данные режисёра
     */
    public void create(Director director) {
        TransactionManager.doInTransaction(entityManager -> entityManager.persist(director));
    }

    /**
     * Получение режисёра по имени
     *
     * @param name имя режисёра
     * @return данные режисёра
     */
    public Director getByName(String name) {
        EntityManager entityManager = DbConnector.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Director> directorCriteriaQuery = criteriaBuilder.createQuery(Director.class);
        Root<Director> root = directorCriteriaQuery.from(Director.class);
        directorCriteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        List<Director> result = entityManager.createQuery(directorCriteriaQuery).getResultList();

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    /**
     * Обновление данных режисёра по идентификатору
     *
     * @param director новые данные режсёра
     */
    public void update(Director director) {
        TransactionManager.doInTransaction(entityManager -> {
            Director attachedEntity = entityManager.merge(director);
            entityManager.persist(attachedEntity);
        });
    }

    /**
     * Удаление данных режисёра по идентификатору
     *
     * @param id идентификатор режисёра
     */
    public void delete(int id) {
        TransactionManager.doInTransaction(entityManager -> {
            Director director = entityManager.find(Director.class, id);
            entityManager.remove(director);
        });
    }
}
