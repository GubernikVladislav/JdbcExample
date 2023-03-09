package org.example.dao;

import org.example.model.Actor;
import org.example.model.Film;
import org.example.utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao-класс для установки взаимосвязей актёров с фильмами
 */
public class ActorFilmDao {

    private static final String INSERT_QUERY =
            "INSERT INTO ACTOR_FILM(ACTOR_ID, FILM_ID) VALUES (?, ?);";

    private static final String GET_ALL_ACTORS_BY_FILM_QUERY = "" +
            "SELECT * FROM ACTOR_FILM AF JOIN ACTOR A ON A.ID = AF.ACTOR_ID WHERE AF.FILM_ID = ?;";

    private static final String GET_ALL_FILMS_BY_ACTOR_QUERY =
            "SELECT * FROM ACTOR_FILM AF JOIN FILM F ON F.ID = AF.FILM_ID WHERE AF.ACTOR_ID = ?;";

    private static final String DELETE_LINK_QUERY =
            "DELETE FROM ACTOR_FILM WHERE ACTOR_ID = ? AND FILM_ID = ?;";

    /**
     * Связь актера с фильмом
     *
     * @param actor данные актера
     * @param film  данные фильма
     */
    public void linkActorWithFilm(Actor actor, Film film) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, actor.getId());
            preparedStatement.setInt(2, film.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение всех актёров фильма
     *
     * @param film данные фильма
     * @return список актёров
     */
    public List<Actor> getAllActorsByFilm(Film film) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ACTORS_BY_FILM_QUERY)) {

            preparedStatement.setInt(1, film.getId());
            ResultSet result = preparedStatement.executeQuery();

            List<Actor> actors = new ArrayList<>();

            while (result.next()) {
                Actor actor = new Actor(result.getInt("id"), result.getString("name"));
                actors.add(actor);
            }

            return actors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение всех фильмов актёра
     *
     * @param actor данные актёра
     * @return список фильмов
     */
    public List<Film> getAllFilmsByActor(Actor actor) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_FILMS_BY_ACTOR_QUERY)) {

            preparedStatement.setInt(1, actor.getId());
            ResultSet result = preparedStatement.executeQuery();

            List<Film> films = new ArrayList<>();

            while (result.next()) {
                Film film = new Film(result.getInt("id"), result.getString("title"), (Integer) result.getObject("director_id"));
                films.add(film);
            }

            return films;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление связи актёра и фильма
     *
     * @param actor данные актера
     * @param film  данные фильма
     */
    public void deleteLink(Actor actor, Film film) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LINK_QUERY)) {

            preparedStatement.setInt(1, actor.getId());
            preparedStatement.setInt(2, film.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
