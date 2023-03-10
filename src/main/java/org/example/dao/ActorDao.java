package org.example.dao;

import org.example.model.Actor;
import org.example.utils.DbConnector;
import org.example.utils.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ActorDao {

    /**
     * Сохранение актёра
     *
     * @param actor данные актёра
     */
    public void create(Actor actor) {
        TransactionManager.doInTransaction((entityManager) -> entityManager.persist(actor));
    }

    /**
     * Получение актёра по имени
     *
     * @param name имя актёра
     * @return данные актёра
     */
    public Actor getByName(String name) {
        EntityManager entityManager = DbConnector.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Actor> actorCriteriaQuery = criteriaBuilder.createQuery(Actor.class);
        Root<Actor> root = actorCriteriaQuery.from(Actor.class);
        actorCriteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        List<Actor> actors = entityManager.createQuery(actorCriteriaQuery).getResultList();
        if (!actors.isEmpty()) {
            return actors.get(0);
        }

        return null;
    }

    /**
     * Обновление данных актёра по идентификатору
     *
     * @param actor новые данные режсёра
     */
    public void update(Actor actor) {
        TransactionManager.doInTransaction(entityManager -> {
            Actor attachedActor = entityManager.merge(actor);
            entityManager.persist(attachedActor);
        });
    }

    /**
     * Удаление данных актёра по идентификатору
     *
     * @param id идентификатор актёра
     */
    public void delete(int id) {
        TransactionManager.doInTransaction(entityManager -> {
            Actor attachedActor = entityManager.find(Actor.class, id);
            entityManager.remove(attachedActor);
        });
    }
}
