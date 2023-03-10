package org.example.dao;

import org.example.model.Actor;
import org.example.model.Film;
import org.example.utils.DbConnector;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Dao-класс для установки взаимосвязей актёров с фильмами
 */
public class ActorFilmDao {

    /**
     * Связь актера с фильмом
     *
     * @param actor данные актера
     * @param film  данные фильма
     */
    public void linkActorWithFilm(Actor actor, Film film) {
        EntityManager entityManager = DbConnector.getEntityManager();
        entityManager.getTransaction().begin();

        Film attachedFilm = entityManager.merge(film);
        Actor attachedActor = entityManager.merge(actor);
        attachedFilm.getActors().add(attachedActor);

        entityManager.persist(attachedFilm);

        entityManager.getTransaction().commit();
    }

    /**
     * Получение всех актёров фильма
     *
     * @param film данные фильма
     * @return список актёров
     */
    public List<Actor> getAllActorsByFilm(Film film) {
        EntityManager entityManager = DbConnector.getEntityManager();
        Film findedFilm = entityManager.find(Film.class, film.getId());

        return findedFilm.getActors().stream().toList();
    }

    /**
     * Получение всех фильмов актёра
     *
     * @param actor данные актёра
     * @return список фильмов
     */
    public List<Film> getAllFilmsByActor(Actor actor) {
        EntityManager entityManager = DbConnector.getEntityManager();
        Actor findedActor = entityManager.find(Actor.class, actor.getId());

        return findedActor.getFilms().stream().toList();
    }

    /**
     * Удаление связи актёра и фильма
     *
     * @param actor данные актера
     * @param film  данные фильма
     */
    public void deleteLink(Actor actor, Film film) {
        EntityManager entityManager = DbConnector.getEntityManager();
        entityManager.getTransaction().begin();

        Film attachedFilm = entityManager.merge(film);
        attachedFilm.removeActor(actor);
        entityManager.persist(attachedFilm);

        entityManager.getTransaction().commit();
    }
}
