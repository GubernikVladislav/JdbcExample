package org.example.dao;

import org.example.model.Film;
import org.example.utils.DbConnector;
import org.example.utils.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FilmDao {

    /**
     * Сохранение фильма
     *
     * @param film данные режисёра
     */
    public void create(Film film) {
        TransactionManager.doInTransaction(entityManager -> entityManager.persist(film));
    }

    /**
     * Получение фильма по названию
     *
     * @param title название фильма
     * @return данные фильма
     */
    public Film getByTitle(String title) {
        EntityManager entityManager = DbConnector.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> filmCriteriaQuery = criteriaBuilder.createQuery(Film.class);
        Root<Film> root = filmCriteriaQuery.from(Film.class);
        filmCriteriaQuery.where(criteriaBuilder.equal(root.get("title"), title));

        List<Film> films = entityManager.createQuery(filmCriteriaQuery).getResultList();
        if (!films.isEmpty()) {
            return films.get(0);
        }

        return null;
    }

    /**
     * Обновление данных фильма
     *
     * @param film данные фильма
     */
    public void update(Film film) {
        TransactionManager.doInTransaction(entityManager -> {
            Film attachedFilm = entityManager.merge(film);
            entityManager.persist(attachedFilm);
        });
    }

    /**
     * Удаление данных фильма по идентификатору
     *
     * @param id идентификатр фильма
     */
    public void delete(int id) {
        TransactionManager.doInTransaction(entityManager -> {
            Film attachedFilm = entityManager.find(Film.class, id);
            attachedFilm.setActors(null);
            entityManager.remove(attachedFilm);
        });
    }
}
